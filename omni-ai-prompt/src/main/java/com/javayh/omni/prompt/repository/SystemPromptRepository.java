package com.javayh.omni.prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.javayh.omni.prompt.entity.SystemPrompt;


/**
 * Repository interface for managing {@link SystemPrompt} entities.
 * Provides methods to retrieve system prompts based on various criteria.
 */
@Repository
public interface SystemPromptRepository extends JpaRepository<SystemPrompt, String> {

    /**
     * Finds a system prompt by its unique identifier.
     *
     * @param id The ID of the prompt to retrieve
     * @return Optional containing the found prompt or empty if not found
     */
    Optional<SystemPrompt> findById(String id);

    /**
     * Retrieves enabled prompts filtered by type.
     *
     * @param promptType The type of prompts to retrieve
     * @return List of enabled system prompts matching the type
     */
    List<SystemPrompt> findByPromptTypeAndEnabledTrue(String promptType);

    /**
     * Retrieves enabled prompts associated with a specific tenant.
     *
     * @param tenantId The tenant identifier
     * @return List of enabled system prompts for the given tenant
     */
    List<SystemPrompt> findByTenantIdAndEnabledTrue(String tenantId);

    /**
     * Retrieves enabled prompts based on language.
     *
     * @param language The language filter for prompts
     * @return List of enabled system prompts in the specified language
     */
    List<SystemPrompt> findByLanguageAndEnabledTrue(String language);

    /**
     * Retrieves all enabled system prompts.
     *
     * @return List of all active system prompts
     */
    List<SystemPrompt> findByEnabledTrue();
}