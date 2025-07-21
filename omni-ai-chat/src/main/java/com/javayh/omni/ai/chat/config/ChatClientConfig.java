package com.javayh.omni.ai.chat.config;

import java.io.IOException;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import com.javayh.omni.ai.chat.chat.entity.SystemPrompt;
import com.javayh.omni.ai.chat.chat.repository.PromptRepository;
import com.javayh.omni.ai.chat.enums.PromptTypeEnum;

@Configuration
public class ChatClientConfig {
    @Autowired
    private PineconeVectorStore vectorStore;

    @Autowired
    private PromptRepository promptRepository;

    /**
     * 创建dashscope的chatClient
     */
    @Bean("dashScopeChatClient")
    public ChatClient dashScopeChatClient(DashScopeChatModel dashScopeChatModel,
                                          JdbcChatMemoryRepository chatMemoryRepository) {

        Advisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
            .documentRetriever(VectorStoreDocumentRetriever.builder()
                .similarityThreshold(0.5)
                .vectorStore(vectorStore)
                .build())
            .queryAugmenter(ContextualQueryAugmenter.builder()
                .allowEmptyContext(true)
                .build())
            .build();
        SystemPrompt prompt = promptRepository.findByPromptTypeAndEnabledTrue(PromptTypeEnum.DEFAULT_SYSTEM.getType());
        return ChatClient.builder(dashScopeChatModel)
            .defaultSystem(prompt.getPrompt())
            .defaultAdvisors(new SimpleLoggerAdvisor())
            .defaultAdvisors(retrievalAugmentationAdvisor)
            .build();
    }

    /**
     * 创建内存
     */
    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder().chatMemoryRepository(chatMemoryRepository).maxMessages(10).build();
    }

    /**
     * 创建内存向量存储
     */
    @Bean
    public PineconeVectorStore vectorStore(
        @Value("${spring.ai.vectorstore.pinecone.api-key}") String apiKey,
        @Value("${spring.ai.vectorstore.pinecone.index-name}") String indexName,
        DashScopeEmbeddingModel embeddingModel) throws IOException {
        // 创建内存向量存储
        return PineconeVectorStore.builder(embeddingModel)
            .apiKey(apiKey).indexName(indexName).build();
    }

}