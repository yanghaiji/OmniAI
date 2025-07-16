package com.javayh.omni.prompt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.javayh.omni.prompt.entity.SystemPrompt;
import com.javayh.omni.prompt.service.SystemPromptService;

@RestController
@RequestMapping("/api/prompts")
public class SystemPromptController {

    @Autowired
    private SystemPromptService service;

    /**
     * 获取所有启用的提示词
     */
    @GetMapping
    public List<SystemPrompt> getAll() {
        return service.getAllPrompts();
    }

    /**
     * 按类型获取
     */
    @GetMapping("/type/{type}")
    public List<SystemPrompt> getByType(@PathVariable String type) {
        return service.getPromptsByType(type);
    }

    /**
     * 按 ID 获取
     */
    @GetMapping("/{id}")
    public Optional<SystemPrompt> getById(@PathVariable String id) {
        return service.getPromptById(id);
    }

    /**
     * 创建
     * 注意：创建操作应该是幂等的，即多次调用创建同一个对象的操作应该不会产生副作用。
     */
    @PostMapping
    public SystemPrompt create(@RequestBody SystemPrompt prompt) {
        return service.createPrompt(prompt);
    }

    /**
     * 更新
     * 注意：更新操作应该是幂等的，即多次调用更新同一个 ID 的操作应该不会产生副作用。
     */
    @PutMapping
    public SystemPrompt update(@RequestBody SystemPrompt prompt) {
        return service.updatePrompt(prompt);
    }

    /**
     * 删除
     * 注意：删除操作应该是幂等的，即多次调用删除同一个 ID 的操作应该不会产生副作用。
     *
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deletePrompt(id);
    }
}