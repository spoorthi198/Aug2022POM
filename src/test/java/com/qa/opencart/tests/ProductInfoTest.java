package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoTest extends BaseTest{
@BeforeClass
    public void productInfoSetUp(){
        acctpage=loginPage.dologin(prop.getProperty("un"),prop.getProperty("pwd"));

    }

    @DataProvider
    public Object[][]  productSelectData(){
        return new Object[][]{
                {"MacBook","MacBook Pro"},
                {"iMac","iMac"},
                {"Samsung","Samsung SyncMaster 941BW"},

        };
    }

    @Test(priority = 1,dataProvider = "productSelectData")
    public void verifyProductDetails(String productSearch,String ProductComp){
        searchPage=acctpage.doSearch(productSearch);
        productinfo=searchPage.selectProduct(ProductComp);
        Map<String, String> info = productinfo.getProductData();
        info.forEach((k,v)-> System.out.println("key"+k+":"+"val"+v));
        info=productinfo.getPricingData();
        info.forEach((k,v)-> System.out.println("key"+k+":"+"val"+v));


        softAssert.assertEquals(productinfo.getProductHeader(),ProductComp);
        softAssert.assertAll();
    }



}
