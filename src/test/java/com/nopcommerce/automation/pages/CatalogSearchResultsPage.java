package com.nopcommerce.automation.pages;

import com.nopcommerce.automation.util.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogSearchResultsPage {

    private By searchProducts = By.xpath("//h2[@class='product-title']");

    public List<String> getAllSearchProducts() {
        Site.scrollIntoViewport(Site.findElement(searchProducts));
        List<WebElement> elements = Site.findElements(searchProducts);

        List<String> productNames = elements.stream().map(WebElement::getText).collect(Collectors.toList());

        // Print each product name
        productNames.forEach(name -> System.out.println("Product found: " + name));

        return productNames;
    }
}

