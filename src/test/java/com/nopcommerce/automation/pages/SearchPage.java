package com.nopcommerce.automation.pages;

import com.nopcommerce.automation.util.Site;
import org.openqa.selenium.By;

public class SearchPage {
    private By productSearchBox = By.xpath("//input[@placeholder='Search store']");
    public By searchButton = By.xpath("//button[@type='submit']");

    public SearchPage enterProductName(String productName) {
        Site.clickwithoutJS(productSearchBox);
        Site.clearandEnterTextBoxDetails(productSearchBox, productName);
        return this;
    }


}