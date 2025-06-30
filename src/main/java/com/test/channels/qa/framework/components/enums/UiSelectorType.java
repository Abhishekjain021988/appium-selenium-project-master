package com.test.channels.qa.framework.components.enums;

import lombok.Getter;

/**
 * @author abhishekjain
 */
@Getter
public enum UiSelectorType {
    CLASSNAME("className"),
    CLASSNAME_MATCHES("classNameMatches"),
    DESCRIPTION("description"),
    DESCRIPTION_CONTAINS("descriptionContains"),
    DESCRIPTION_MATCHES("descriptionMatches"),
    DESCRIPTION_STARTSWITH("descriptionStartsWith"),
    INDEX("index"),
    INSTANCE("instance"),
    PACKAGENAME("packageName"),
    PACKAGENAME_MATHCES("packageNameMatches"),
    RESOURCEID("resourceId"),
    RESOURCEID_MATCHES("resourceIdMatches"),
    TEXT("text"),
    TEXT_CONTAINS("textContains"),
    TEXT_MATCHES("textMatches"),
    TEXT_STRATSWITH("textStartsWith");

    private String selector;

    UiSelectorType(String selector) {
        this.selector = selector;
    }
}
