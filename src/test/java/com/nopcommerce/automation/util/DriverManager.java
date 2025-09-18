package com.nopcommerce.automation.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public enum BrowserType {
        CHROME, FIREFOX, EDGE
    }

    // Initialize driver for current thread
    public static void initDriver(String browserName) {
        if (driver.get() != null) return; // already initialized

        BrowserType browser;

        // Normalize browser name
        String name = browserName.trim().toLowerCase();

        switch (name) {
            case "chrome":
            case "chromelocal":
            case "chromeremote":
                browser = BrowserType.CHROME;
                break;

            case "firefox":
            case "firefoxlocal":
                browser = BrowserType.FIREFOX;
                break;

            case "edge":
            case "edgelocal":
                browser = BrowserType.EDGE;
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized", "--disable-notifications");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver.set(new EdgeDriver(edgeOptions));
                break;
        }

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
