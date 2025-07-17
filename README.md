# OmniAI

# Information

OmniAI is a project that aims to create a virtual assistant that can be used to perform various tasks. The virtual
assistant is able to perform tasks such as:

- chat with users
- rag based search
- prompt engineering

# OmniAI-prompt

OmniAI-prompt is a project that aims to create a prompt engineering tool for OmniAI. The prompt engineering tool is
able to generate prompts for OmniAI based on the task that the user wants to perform. The prompt engineering tool is
able to generate prompts for the following tasks:

```
### 创建Prompt
POST localhost:8081/api/prompts
Content-Type: application/json

{
  "id": "prompt_001",
  "prompt": "You are a helpful assistant",
  "promptType": "system",
  "language": "en-US",
  "tenantId": "tenant_abc",
  "enabled": true,
  "isDefault": false,
  "version": 2,
  "priority": 90,
  "description": "A sample system prompt for English users.",
  "createdBy": "admin",
  "cacheTtl": 7200
}

### 查询所有Prompt
GET localhost:8081/api/prompts


```

# OmniAI-RAG
OmniAI-RAG is a project that aims to create a RAG (Retrieval Augmented Generation) tool for OmniAI. The RAG tool is
able to perform RAG based search for OmniAI. The RAG tool is able to perform RAG based search for the following tasks:

- uploading documents (docx, pdf, txt, html,json, csv, xlsx,...)

 ```    
### 上传文件
POST localhost:8083/api/rag/upload 

### 批量上传文件
POST localhost:8083/api/rag/batch-upload

