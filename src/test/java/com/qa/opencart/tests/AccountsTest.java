package com.qa.opencart.tests;

import com.qa.opencart.utills.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsTest extends  BaseTest{

 @BeforeClass
 public void acctPageSetUP(){
     acctpage=loginPage.dologin(prop.getProperty("un"),prop.getProperty("pwd"));
 }


 @Test(priority = 1)
 public void getTitle(){
     String title = acctpage.getTitle();
     Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
 }


 @Test(priority = 2)
    public void isLinkPresent(){
    Assert.assertTrue(acctpage.islogoutlinkexit());
 }

@Test(priority = 3)
    public void compareAcctList(){
    List<String> actualList = acctpage.verifyAcctDesc();
     Assert.assertEquals(actualList,Constants.getExpectedArraylist());
}

@Test(priority = 4)
    public void isSearchFieldPresent(){
     Assert.assertTrue(acctpage.isSearchFieldexit());
}
@DataProvider
public Object[][]  productData(){
     return new Object[][]{
        {"MacBook Air"},
        {"Apple"},
        {"Samsung"},

    };
}

@Test(priority = 5,dataProvider = "productData")
    public void doSearch(String product){
     searchPage=acctpage.doSearch(product);
     Assert.assertTrue(searchPage.getProductCount()>0);
}
    @DataProvider
    public Object[][]  productSelectData(){
        return new Object[][]{
                {"MacBook","MacBook Pro"},
                {"iMac","iMac"},
                {"Samsung","Samsung SyncMaster 941BW"},

        };
    }


@Test(priority = 6,dataProvider = "productSelectData")
    public void selectProductTest(String productSearch,String ProductComp){
     searchPage=acctpage.doSearch(productSearch);
       productinfo=searchPage.selectProduct(ProductComp);
       Assert.assertEquals(productinfo.getProductHeader(),ProductComp);
}
}
