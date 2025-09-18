package com.nopcommerce.automation.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("LoginData")
public class LoginTestDataModel {

    @ExcelCellName("url")
    public String url;

    @ExcelCellName("Username")
    public String username;

    @ExcelCellName("Password")
    public String password;

}