package com.test.channels.qa.framework.components.enums;

import org.openqa.selenium.By;

/**
 * Type of the picker wheel values
 *
 * @author abhishekjain
 */
public enum LocatorType {

    ID("id"),
    XPATH("xpath"),
    CLASSNAME("className"),
    TAGNAME("tagName"),
    LINKTEXT("linkText"),
    ACCESSIBILITYID("AccessibilityId");

    private String locator;

    LocatorType(String locator) {
        this.locator = locator;
    }

    public String getLocatorType() {
        return locator;
    }

    public By getByLocatorType(String value) {
        By by = null;
        if(getLocatorType().equals("id"))
            return By.id(value);
        else if(getLocatorType().equals("id"))
            return By.xpath(value);
        return by;
    }

}
