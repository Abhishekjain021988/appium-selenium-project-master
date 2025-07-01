package com.test.channels.qa.unit.framework.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.test.channels.qa.framework.utils.PropertyUtil;
import org.testng.annotations.Test;

import java.util.Properties;

public class PropertyUtilTest {
    
    private static final String PROP_FILE = "unit-test.properties";

    @Test
    public void testLoadProperties() {
        // load default properties
        Properties properties = PropertyUtil.loadProperties();
        assertNotNull(properties);
        assertEquals("Android", properties.get("android.platform_name"));
        
        // load custom properties
        properties = PropertyUtil.loadProperties(PROP_FILE);
        assertNotNull(properties);
        assertEquals("Test", properties.get("test.string.key"));
    }

    @Test
    public void testGetString() {
        assertEquals("iPhone X", PropertyUtil.get("ios.device_name"));
        assertEquals("Test", PropertyUtil.get("test.string.key", PROP_FILE));
    }

    @Test
    public void testGetInt() {
        assertEquals(10, PropertyUtil.getInt("implicit_timeout"));
        assertEquals(100, PropertyUtil.getInt("test.integer.key", PROP_FILE));
    }

}
