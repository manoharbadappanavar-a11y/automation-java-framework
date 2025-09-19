package com.nopcommerce.automation.pages;

import com.nopcommerce.automation.util.Site;
import com.nopcommerce.automation.model.LoginTestDataModel;
import org.openqa.selenium.By;

public class LoginPage {

    private By loginLinkByClass = By.className("ico-login");

    private By usernameEmailInput = By.className("email");
    private By passwordInput = By.className("password");
    private By loginButton = By.className("login-button");
    public By loginError = By.xpath("//div[@class='message-error validation-summary-errors']");

    public LoginPage enterUsername(String username) {
        Site.click(loginLinkByClass);
        Site.clearandEnterTextBoxDetails(usernameEmailInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        Site.clearandEnterTextBoxDetails(passwordInput, password);
        return this;
    }

    public LoginPage clickLogin() {
        Site.click(loginButton);
        return this;
    }

}