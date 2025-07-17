package com.javayh.omni.rag.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.javayh.omni.rag.service.RagUploadService;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-16
 */
@Service
public class TikaRagUploadServiceImpl implements RagUploadService {

    @Autowired
    private TokenTextSplitter tokenTextSplitter;

    @Autowired
    private PineconeVectorStore vectorStore;

    /**
     * 上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     * @param file 原始文件
     * @return
     * @throws IOException
     */
    @Override
    public String upload(MultipartFile file) throws IOException {
        // 读取文件
        TikaDocumentReader textReader = new TikaDocumentReader(new InputStreamResource(file.getInputStream(), file.getOriginalFilename()));
        List<Document> documents = textReader.read();

        // 分割文档
        List<Document> splitDocs = tokenTextSplitter.apply(documents);
        // 创建内存向量存储
        vectorStore.add(splitDocs);
        return "";
    }

    /**
     * 批量上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     *
     * @param file 原始文件
     * @return
     */
    @Override
    public String batchUpload(List<MultipartFile> file) throws IOException {
        for (MultipartFile multipartFile : file) {
            upload(multipartFile);
        }
        return "";
    }


}
