package com.qa.opencart.tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utills.Constants;
import io.qameta.allure.*;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Epic("Epic no:100 testing open cart login feature")
@Story("Testing user Login experience ")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{

    @Test(priority = 1)
    @Description("Login Page title Test")
    @Severity(SeverityLevel.MINOR)
    public void loginPageTitleTest(){
        String acttitle = loginPage.getTitle();
        System.out.println("title:"+ acttitle);
        Assert.assertEquals(acttitle, Constants.LOGIN_PAGE_TITLE);

    }
    @Test(priority = 2)
    @Description("Get login page url test")
    @Severity(SeverityLevel.NORMAL)
    public void GetLoginPageUrl(){
        String acturl = loginPage.getCurrenturl();
        System.out.println(acturl);
        Assert.assertTrue(acturl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
    }

    @Test(priority = 3)
    @Description("Forgot Pwd link present test")
    @Severity(SeverityLevel.CRITICAL)
    public void LinksPresent(){
        Assert.assertTrue(loginPage.forgotpasswordlinkExist());
        Assert.assertTrue(loginPage.forgotpasswordlinkExist());
    }


    @Test(priority = 4)
    @Description("User login test")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest(){
        loginPage.dologin(prop.getProperty("un").trim(),prop.getProperty("pwd").trim());

    }
}
