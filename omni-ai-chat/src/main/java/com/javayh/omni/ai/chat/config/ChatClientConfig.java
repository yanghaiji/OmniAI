package com.javayh.omni.ai.chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;

@Configuration
public class ChatClientConfig {


    @Bean("dashScopeChatClient")
    public ChatClient dashScopeChatClient(DashScopeChatModel dashScopeChatModel,
                                          JdbcChatMemoryRepository chatMemoryRepository) {
        return ChatClient.builder(dashScopeChatModel)
            .defaultSystem("你是一个AI助手，你可以回答任何问题。回答时加一些表情符号。")
            .defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder().chatMemoryRepository(chatMemoryRepository).maxMessages(10).build();
    }

}