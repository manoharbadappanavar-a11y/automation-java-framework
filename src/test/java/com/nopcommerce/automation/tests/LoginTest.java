package com.nopcommerce.automation.tests;
import com.nopcommerce.automation.util.Site;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends  BaseTest {


    @Test
    public void loginwithValidData(){
        login.enterUsername(loginList.get(4).username);
        login.enterPassword(loginList.get(4).password);
        login.clickLogin();
        if (Site.isElementVisible(login.loginError)) {
            String errorText = Site.findElement(login.loginError).getText();
            System.out.println("Error message: " + errorText);

            Assert.fail("Login failed with error: " + errorText);
        } else {
            System.out.println("Login successful");
        }
    }
}
