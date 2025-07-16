CREATE TABLE system_prompt (
    id VARCHAR(50) PRIMARY KEY,
    prompt TEXT NOT NULL,
    prompt_type VARCHAR(50) NOT NULL,
    language VARCHAR(10) DEFAULT 'zh-CN', -- 支持多语言
    tenant_id VARCHAR(50),                -- 支持多租户
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    is_default BOOLEAN DEFAULT FALSE,     -- 是否为默认
    version INT DEFAULT 1,                -- 版本号
    priority INT DEFAULT 100,             -- 优先级
    description TEXT,                     -- 描述
    created_by VARCHAR(50),               -- 创建者
    cache_ttl INT DEFAULT 3600,           -- 缓存过期时间（秒）
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_used DATETIME                    -- 最后使用时间
);