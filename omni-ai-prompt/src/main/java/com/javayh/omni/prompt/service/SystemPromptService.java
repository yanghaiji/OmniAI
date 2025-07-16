package com.javayh.omni.prompt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.javayh.omni.prompt.entity.SystemPrompt;
import com.javayh.omni.prompt.repository.SystemPromptRepository;

@Service
public class SystemPromptService {

    @Autowired
    private SystemPromptRepository repository;

    /**
     * Retrieves all enabled system prompts.
     * @return List of active system prompts
     */
    public List<SystemPrompt> getAllPrompts() {
        return repository.findByEnabledTrue();
    }

    /**
     * Retrieves enabled prompts filtered by prompt type.
     * @param promptType The type of prompts to filter by
     * @return List of enabled prompts matching the type
     */
    public List<SystemPrompt> getPromptsByType(String promptType) {
        return repository.findByPromptTypeAndEnabledTrue(promptType);
    }

    /**
     * Retrieves enabled prompts for a specific tenant.
     * @param tenantId The tenant identifier to filter by
     * @return List of enabled prompts associated with the tenant
     */
    public List<SystemPrompt> getPromptsByTenant(String tenantId) {
        return repository.findByTenantIdAndEnabledTrue(tenantId);
    }

    /**
     * Retrieves enabled prompts in a specific language.
     * @param language The language code to filter by
     * @return List of enabled prompts in the specified language
     */
    public List<SystemPrompt> getPromptsByLanguage(String language) {
        return repository.findByLanguageAndEnabledTrue(language);
    }

    /**
     * Finds a system prompt by its unique identifier.
     * @param id The ID of the prompt to retrieve
     * @return Optional containing the found prompt or empty if not found
     */
    public Optional<SystemPrompt> getPromptById(String id) {
        return repository.findById(id);
    }

    /**
     * Creates and persists a new system prompt.
     * @param prompt The prompt entity to create
     * @return The newly created system prompt
     */
    public SystemPrompt createPrompt(SystemPrompt prompt) {
        prompt.setUpdatedAt(LocalDateTime.now());
        prompt.setLastUsed(LocalDateTime.now());
        return repository.save(prompt);
    }

    /**
     * Updates an existing system prompt.
     * @param prompt The prompt entity with updated values
     * @return The updated system prompt
     */
    public SystemPrompt updatePrompt(SystemPrompt prompt) {
        prompt.setUpdatedAt(LocalDateTime.now());
        prompt.setLastUsed(LocalDateTime.now());
        return repository.save(prompt);
    }

    /**
     * Deletes a system prompt by its ID.
     * @param id The ID of the prompt to delete
     */
    public void deletePrompt(String id) {
        repository.deleteById(id);
    }
}