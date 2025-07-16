package com.javayh.omni.prompt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "system_prompt")
public class SystemPrompt {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String prompt;

    @Column(name = "prompt_type", nullable = false)
    private String promptType;

    @Column(length = 10)
    private String language = "zh-CN";

    private String tenantId;

    private boolean enabled = true;

    private boolean isDefault = false;

    private int version = 1;

    private int priority = 100;

    private String description;

    private String createdBy;

    private Integer cacheTtl = 3600;

    @Column(updatable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime lastUsed;

}