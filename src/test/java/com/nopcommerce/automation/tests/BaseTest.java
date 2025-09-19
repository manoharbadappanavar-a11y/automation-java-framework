package com.nopcommerce.automation.tests;

import com.nopcommerce.automation.model.LoginTestDataModel;
import com.nopcommerce.automation.pages.HomePage;
import com.nopcommerce.automation.util.ConfigReader;
import com.nopcommerce.automation.util.DriverManager;
import com.nopcommerce.automation.util.FileUtil;
import com.nopcommerce.automation.util.Site;
import com.poiji.bind.Poiji;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.List;

public class BaseTest {

    public FileUtil fileUtil;
    public File dataFile;
    public HomePage homePage;
    public List<LoginTestDataModel> loginList;

    public BaseTest() {
        fileUtil = FileUtil.getInstance();
        dataFile = fileUtil.getFile();
        // Load login test data
        loginList = Poiji.fromExcel(dataFile, LoginTestDataModel.class, fileUtil.getPoijiOptions());
    }

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        String browser = ConfigReader.getBrowser();
        DriverManager.initDriver(browser);

        // Maximize browser
        Site.maximizeWindow();

        // Initialize page object AFTER driver is ready
        homePage = new HomePage();

        homePage.goToHomePage(loginList.get(0).url);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
