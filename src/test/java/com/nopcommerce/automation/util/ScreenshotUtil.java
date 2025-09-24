package com.nopcommerce.automation.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(String screenshotName) {
        WebDriver driver = DriverManager.getDriver();

        try {
            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Add timestamp to avoid overwriting
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String dest = System.getProperty("user.dir") +
                    "/test-output/screenshots/" + screenshotName + "_" + timestamp + ".png";

            File target = new File(dest);

            // Ensure directory exists
            Files.createDirectories(target.getParentFile().toPath());

            // Copy screenshot file
            Files.copy(src.toPath(), target.toPath());

            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
