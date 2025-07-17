package com.javayh.omni.rag.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-16
 */
public interface RagUploadService {

    /**
     * 上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     *
     * @param file 原始文件
     * @return
     */
    String upload(MultipartFile file) throws IOException;



    /**
     * 批量上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     *
     * @param file 原始文件
     * @return
     */
    String batchUpload(List<MultipartFile> file) throws IOException;
}
