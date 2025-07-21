package com.javayh.omni.ai.chat.chat.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javayh.omni.ai.chat.chat.ChatRequestDto;
import com.javayh.omni.ai.chat.chat.entity.SystemPrompt;
import com.javayh.omni.ai.chat.chat.repository.PromptRepository;
import com.javayh.omni.ai.chat.chat.service.ChatAIService;
import com.javayh.omni.ai.chat.enums.PromptTypeEnum;

import reactor.core.publisher.Flux;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
@Service
public class ChatAIServiceImpl implements ChatAIService {


    @Autowired
    private ChatClient dashScopeChatClient;

    @Autowired
    private ChatMemory chatMemory;

    @Autowired
    private PromptRepository promptRepository;

    /**
     * 提问
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    @Override
    public String ask(ChatRequestDto chatRequestDto) {
        String conversationId = chatRequestDto.getConversationId();
        chatMemory.add(conversationId, new UserMessage(chatRequestDto.getQuestion()));
        List<Message> messages = chatMemory.get(conversationId);
        ChatClient.CallResponseSpec call = dashScopeChatClient.prompt().messages(messages).call();
        String content = call.content();
        // 将ai的回答保存到数据库中
        if (content != null) {
            chatMemory.add(conversationId, new AssistantMessage(content));
        }
        return content;
    }

    /**
     * 流式提问
     * <p>
     * 注意：该方法会阻塞线程，直到回答结束
     * </p>
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    @Override
    public Flux<String> askStream(ChatRequestDto chatRequestDto) {
        // 用于拼接完整内容
        AtomicReference<String> fullContent = new AtomicReference<>("");
        String conversationId = chatRequestDto.getConversationId();
        List<Message> messagesSys = chatMemory.get(conversationId);
        if (messagesSys.isEmpty()) {
            SystemPrompt systemPrompt = promptRepository.findByPromptTypeAndEnabledTrue(PromptTypeEnum.SYSTEM.getType());
            chatMemory.add(conversationId, new SystemMessage(systemPrompt.getPrompt()));
        }
        chatMemory.add(conversationId, new UserMessage(chatRequestDto.getQuestion()));
        List<Message> messages = chatMemory.get(conversationId);
        Flux<String> call = dashScopeChatClient.prompt().messages(messages)
            // 流式返回
            .stream()
            .content()
            .doOnNext(chunk -> {
                // 每收到一个片段就追加到 fullContent 中
                fullContent.updateAndGet(s -> s + chunk);
            });

        // 使用 doOnComplete 在流结束后保存完整内容到数据库
        return call.doOnComplete(() -> {
            String content = fullContent.get();
            if (content != null && !content.isEmpty()) {
                chatMemory.add(conversationId, new AssistantMessage(content));
            }
        });
    }

}
