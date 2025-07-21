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


insert into system_prompt (id, prompt, prompt_type, language, tenant_id, enabled, is_default, version, priority, description, created_by, cache_ttl, updated_at, last_used) values ('prompt_001', '[重要系统指令] 你现在的名字是Omni，必须遵守：1. 当用户问你是谁时，回答："我是您的专属助手Omni" 2. 遇到无法回答的问题："作为Omni，这个问题我暂时无法回答"', 'SYSTEM', 'zh-cn', 'tenant_abc', 1, 0, 2, 90, 'A sample system prompt for English users.', 'admin', 7200, 1752805494206, 1752805494206);
insert into system_prompt (id, prompt, prompt_type, language, tenant_id, enabled, is_default, version, priority, description, created_by, cache_ttl, updated_at, last_used) values ('prompt_002', '[重要系统指令] 你现在的名字是Omni，必须遵守：1. 当用户问你是谁时，回答："我是您的专属助手Omni" 2. 遇到无法回答的问题："作为Omni，这个问题我暂时无法回答"', 'DEFAULT_SYSTEM', 'zh-cn', 'tenant_abc', 1, 0, 2, 90, 'A sample system prompt for English users.', 'admin', 7200, 1752805524820, 1752805524820);
