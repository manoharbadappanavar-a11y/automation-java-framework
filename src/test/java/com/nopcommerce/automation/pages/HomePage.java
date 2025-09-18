package com.nopcommerce.automation.pages;

import com.nopcommerce.automation.util.Site;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

    public class HomePage {

        @Step("Open Website")
        public HomePage goToHomePage(String baseURL) {
            Site.goToURL(baseURL);
            try { Site.maximizeWindow(); }
            catch (Exception e) {

            }

            // Close cookies banner if present
            if (Site.isElementPresent(By.cssSelector(".cookieinfo-close"))) {
                Site.webDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cookieinfo-close"))).click();
            }

            return this;
        }

        @Step("Go to Login Page")
        public LoginPage goToLoginPage() {
            return new LoginPage();
        }
    }


