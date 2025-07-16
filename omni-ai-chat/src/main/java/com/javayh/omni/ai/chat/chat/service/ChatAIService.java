package com.javayh.omni.ai.chat.chat.service;

import org.springframework.http.codec.ServerSentEvent;
import com.javayh.omni.ai.chat.chat.ChatRequestDto;

import reactor.core.publisher.Flux;

/**
 * <p>
 * 聊天接口
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
public interface ChatAIService {

    /**
     * 提问
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    String ask(ChatRequestDto chatRequestDto);


    /**
     * 流式提问
     * <p>
     * 注意：该方法会阻塞线程，直到回答结束
     * </p>
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    Flux<String> askStream(ChatRequestDto chatRequestDto);
}
