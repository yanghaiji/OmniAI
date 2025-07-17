package com.javayh.omni.rag.config;

import java.io.IOException;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-16
 */
@Configuration
public class RagConfig {


    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    @Bean
    public PineconeVectorStore vectorStore(
        @Value("${spring.ai.vectorstore.pinecone.api-key}") String apiKey,
        @Value("${spring.ai.vectorstore.pinecone.index-name}") String indexName,
        DashScopeEmbeddingModel embeddingModel) throws IOException {
        // 创建内存向量存储
        return PineconeVectorStore.builder(embeddingModel)
            .apiKey(apiKey).indexName(indexName).build();
    }

//    @Bean
//    public PineconeVectorStore vectorStore(
//        @Value("classpath:/knowledge.pdf") Resource pdfResource,
//        @Value("spring.ai.vectorstore.pinecone.api-key") String apiKey,
//        @Value("spring.ai.vectorstore.pinecone.index-name") String indexName,
//        TokenTextSplitter tokenTextSplitter, DashScopeEmbeddingModel embeddingModel) throws IOException {
//
//        // 配置PDF阅读器
//        PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
//            .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
//                .withNumberOfTopTextLinesToDelete(3)
//                .build())
//            .build();
//
//        // 读取PDF文档
//        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfResource, config);
//        List<Document> documents = pdfReader.get();
//
//        // 分割文档
//        List<Document> splitDocs = tokenTextSplitter.apply(documents);
//
//        // 创建内存向量存储
//        PineconeVectorStore vectorStore =
//            PineconeVectorStore.builder(embeddingModel)
//                .apiKey(apiKey).indexName(indexName).build();
//        vectorStore.add(splitDocs);
//        return vectorStore;
//    }

}
