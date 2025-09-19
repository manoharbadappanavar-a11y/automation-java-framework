package com.nopcommerce.automation.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("SearchInputData") // Name of the sheet in Excel
public class SearchTestDataDetailsModel {

    @ExcelCellName("TestMethodName")
    public String testMethodName;

    @ExcelCellName("ProductName")
    public String productName;

    @ExcelCellName("ProductId")
    public int productId;

    @ExcelCellName("ProductCategory")
    public String productCategory;

}
