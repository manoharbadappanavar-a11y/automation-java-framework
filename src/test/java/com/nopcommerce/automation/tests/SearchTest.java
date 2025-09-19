package com.nopcommerce.automation.tests;

import com.nopcommerce.automation.model.SearchTestDataDetailsModel;
import com.nopcommerce.automation.pages.CatalogSearchResultsPage;
import com.nopcommerce.automation.pages.SearchPage;
import com.nopcommerce.automation.util.Site;
import com.poiji.bind.Poiji;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {

    private List<SearchTestDataDetailsModel> searchData;
    private SearchPage searchPage;
    private CatalogSearchResultsPage resultPage;

    @BeforeMethod
    public void loadTestData() {
        searchData = Poiji.fromExcel(dataFile, SearchTestDataDetailsModel.class, fileUtil.getPoijiOptions());
        searchPage = new SearchPage();
        resultPage = new CatalogSearchResultsPage();
    }

    @Test(description = "Verify that user perform the product search")
    public void testSearchProduct() {
        searchPage.enterProductName(searchData.get(0).productName);
        Site.click(searchPage.searchButton);
        Assert.assertTrue(resultPage.getAllSearchProducts().stream().allMatch(p -> p.contains("Apple")), "Some products in search results do not contain 'Apple'");
    }
}
