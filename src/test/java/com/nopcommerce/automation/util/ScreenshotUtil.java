package com.nopcommerce.automation.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String captureScreenshot(String screenshotName) {
        WebDriver driver = DriverManager.getDriver(); // get the driver from DriverManager
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + ".png";
            File target = new File(dest);
            Files.createDirectories(target.getParentFile().toPath());
            Files.copy(src.toPath(), target.toPath());
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
