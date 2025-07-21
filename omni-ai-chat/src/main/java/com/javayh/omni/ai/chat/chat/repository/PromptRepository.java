package com.javayh.omni.ai.chat.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javayh.omni.ai.chat.chat.entity.SystemPrompt;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-18
 */
@Repository
public interface PromptRepository extends JpaRepository<SystemPrompt, String> {

    /**
     * 根据类型和启用状态查询系统提示
     *
     * @param promptType 提示类型
     * @return 系统提示
     */
    SystemPrompt findByPromptTypeAndEnabledTrue(String promptType);

}
