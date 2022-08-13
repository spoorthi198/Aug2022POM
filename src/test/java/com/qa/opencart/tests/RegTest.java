package com.qa.opencart.tests;

import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utills.Constants;
import org.apache.hc.core5.reactor.Command;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Properties;


public class RegTest extends  BaseTest{

    @BeforeClass
    public void regPageSetUP(){
        regPage=loginPage.goToRegPage();
    }


@Test(priority = 1)
    public void testRegPageTitle(){

    String actTitle = regPage.getTitle();
    Assert.assertEquals(actTitle, Constants.REGISTER_PAGE_TITLE);

    }
@DataProvider
    public Object[][] RegData(){
        return new Object[][]{
      {"Jnana","R","jnana34@r.com","45562","abcdef","abcdef"},
                {"Jnana1","d","jnana15@r.com","45562","abcdef","abcdef"}

        };
    }


    @Test(priority = 2,dataProvider = "RegData")
    public void testRwgForm(String fn,String ln,String eml,String tel,String pwd,String cpwd){
        regPage.doFillTheRegForm(fn,ln,eml,tel,pwd,cpwd);
        regPage.testAccountCreation();
        regPage.doclicklogout();
        regPage.doClickRegLink();


    }


//@Test(priority = 4)
//    public void testRwgForm(){
//        regPage.doFillTheRegForm(prop.getProperty("fn"),prop.getProperty("ln"),prop.getProperty("eml"),prop.getProperty("tel"),prop.getProperty("pw")
//        ,prop.getProperty("cpw"));
//}

//@Test(priority = 5)
//    public void testAcctCreation(){
//    String msg = regPage.testAccountCreation();
//    Assert.assertEquals(msg,Constants.ACCOUNT_CREATED);
//}
}
