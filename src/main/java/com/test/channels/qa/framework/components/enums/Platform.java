package com.test.channels.qa.framework.components.enums;

/**
 * The mobile platform types
 * @author abhishekjain
 *
 */
public enum Platform {
    
    ANDROID("Android"), IOS("iOS"), CHROME("Chrome");
    
    public final String platform;
    
    /**
     * Create a new instance of the enum
     * @param platform - the platform to set
     */
    Platform(String platform) {
        this.platform = platform;
    }
    
    /**
     * Get a platfrom from the enum type
     * @param value - the string value
     * @return PlatformType that corresponds to the value or throws IllegalArgumentException
     */
    public static Platform fromString(String value) {
        if(Platform.ANDROID.platform.equalsIgnoreCase(value)) {
            return Platform.ANDROID;
        } else if(Platform.IOS.platform.equalsIgnoreCase(value)) {
            return Platform.IOS;
        } else if(Platform.CHROME.platform.equalsIgnoreCase(value)) {
            return Platform.CHROME;
        }
        
        throw new IllegalArgumentException("Invalid platform " + value);
    }
}
