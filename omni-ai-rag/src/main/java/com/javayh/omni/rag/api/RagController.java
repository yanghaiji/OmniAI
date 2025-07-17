package com.javayh.omni.rag.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/api/rag")
public class RagController {

    @Autowired
    private RagUploadService ragUploadService;

    /**
     * 上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        ragUploadService.upload(file);
        return "OK";
    }


    /**
     * 上传文件
     * <p>
     * 支持 doc docx txt pdf等
     * </p>
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/batch-upload")
    public String batchUpload(@RequestParam(value = "file") List<MultipartFile> file) throws IOException {
        ragUploadService.batchUpload(file);
        return "OK";
    }



}
