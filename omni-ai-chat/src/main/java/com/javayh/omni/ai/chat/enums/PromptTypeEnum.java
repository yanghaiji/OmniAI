package com.javayh.omni.ai.chat.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-18
 */
public enum PromptTypeEnum {

    SYSTEM("SYSTEM", "系统"),

    DEFAULT_SYSTEM("DEFAULT_SYSTEM", "默认系统"),

    USER("USER", "用户"),

    ASSISTANT("ASSISTANT", "助手"),

    ;

    private final String type;
    private final String description;

    PromptTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static PromptTypeEnum getByType(String type) {
        for (PromptTypeEnum value : PromptTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

    public static PromptTypeEnum getByDescription(String description) {
        for (PromptTypeEnum value : PromptTypeEnum.values()) {
            if (value.getDescription().equals(description)) {
                return value;
            }
        }
        return null;
    }

    public static String getDescriptionByType(String type) {
        for (PromptTypeEnum value : PromptTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getDescription();
            }
        }
        return null;
    }

    public static String getTypeByDescription(String description) {
        for (PromptTypeEnum value : PromptTypeEnum.values()) {
            if (value.getDescription().equals(description)) {
                return value.getType();
            }
        }
        return null;
    }

}
