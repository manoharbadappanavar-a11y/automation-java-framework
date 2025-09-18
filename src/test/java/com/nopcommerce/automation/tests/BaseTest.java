package com.nopcommerce.automation.tests;

import com.nopcommerce.automation.model.LoginTestDataModel;
import com.nopcommerce.automation.pages.HomePage;
import com.nopcommerce.automation.pages.LoginPage;
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
    protected LoginPage login = new LoginPage();
    protected HomePage home = new HomePage();
    protected List<LoginTestDataModel> loginList;

    public FileUtil fileUtil;
    public File dataFile;

    public BaseTest() {
        fileUtil = FileUtil.getInstance();
        dataFile = fileUtil.getFile();
        loginList = Poiji.fromExcel(dataFile, LoginTestDataModel.class, fileUtil.getPoijiOptions());
    }

    @BeforeMethod
    public void setUp() {
        // Initialize driver using config
        String browser = ConfigReader.getBrowser();
        DriverManager.initDriver(browser);
        Site.maximizeWindow();

        // Navigate to first URL from Excel
        home.goToHomePage(loginList.get(0).url);
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver(); // Close the browser
    }
}
