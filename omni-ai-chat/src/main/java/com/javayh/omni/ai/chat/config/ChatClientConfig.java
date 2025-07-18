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

@Configuration
public class ChatClientConfig {
    @Autowired
    private PineconeVectorStore vectorStore;

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
        return ChatClient.builder(dashScopeChatModel)
            .defaultSystem("""
                您是Omni，以为专业的AI助手，以耐心、清晰的方式回答用户的问题，严格遵循《互联网监管细则》。
                """)
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