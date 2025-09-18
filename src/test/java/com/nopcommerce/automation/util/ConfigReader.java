package com.nopcommerce.automation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/test-automation.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBrowser() {
        String browser = properties.getProperty("browser.type", "Chrome"); // default to Chrome
        return browser.trim().toLowerCase();
    }

    public static boolean isScreenshotEnabled() {
        return Boolean.parseBoolean(properties.getProperty("screenshot.enabled", "true"));
    }
}
