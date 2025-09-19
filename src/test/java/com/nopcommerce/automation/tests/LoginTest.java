package com.nopcommerce.automation.tests;

import com.nopcommerce.automation.model.LoginTestDataModel;
import com.nopcommerce.automation.pages.LoginPage;
import com.nopcommerce.automation.util.Site;
import com.poiji.bind.Poiji;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private List<LoginTestDataModel> loginList;

    @BeforeMethod
    public void setUpTestDataAndPages() {
        // Load login test data
        loginList = Poiji.fromExcel(dataFile, LoginTestDataModel.class, fileUtil.getPoijiOptions());

        // Initialize page object
        loginPage = new LoginPage();

    }
        @Test
        public void loginWithValidData() {
            loginPage.enterUsername(loginList.get(4).username);
            loginPage.enterPassword(loginList.get(4).password);
            loginPage.clickLogin();

            if (Site.isElementVisible(loginPage.loginError)) {
                String errorText = Site.findElement(loginPage.loginError).getText();
                System.out.println("Error message: " + errorText);
                Assert.fail("Login failed with error: " + errorText);
            } else {
                System.out.println("Login successful");
            }
        }
    }
