package com.nopcommerce.automation.util;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.nopcommerce.automation.model.LoginTestDataModel;
import com.nopcommerce.automation.tests.BaseTest;
import com.poiji.bind.Poiji;

public class DataProviderClass extends BaseTest {

    @DataProvider(name = "LoginDetails")
    public Object[][] loginData() {
        List<LoginTestDataModel> loginList =
                Poiji.fromExcel(dataFile, LoginTestDataModel.class, fileUtil.getPoijiOptions());

        return new Object[][] {
                { loginList }
        };
    }

}
