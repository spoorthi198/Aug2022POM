package com.qa.opencart.pages;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  {
    //1. create private driver
    private WebDriver driver;
    private ElementUtil eleUtil;
    // 2. initialize the driver using class constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
        eleUtil=new ElementUtil(driver);
    }
    // 3. create By Locators
   private By emailid = By.id("input-email");
    private  By password = By.id("input-password");
    private  By login = By.xpath("//input[@type='submit']");
    private By forgotpasswordLink = By.linkText("Forgotten Password");
    private By registerLink=By.linkText("Register");
    private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");


    @Step("Testing login page title")
    public String  getTitle(){
        return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }
    @Step("Getting current page url")
    public String getCurrenturl(){
        return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
    }
    @Step("Testing forgot password lnk on login page")
    public boolean forgotpasswordlinkExist(){
        return eleUtil.doIsDisplayed(forgotpasswordLink);
    }
    public boolean reglinkExist(){
        return eleUtil.doIsDisplayed(registerLink);
    }

    @Step("Testing user can login or not?")
    public AccountsPage dologin(String un,String pwd){
        System.out.println("my creds:"+un +"and"+pwd);
        eleUtil.doSendKeys(emailid,un);
        eleUtil.doSendKeys(password,pwd);
        eleUtil.doClick(login);
        return new AccountsPage(driver);

    }

@Step("Teing negative login")
    public boolean doLoginWithWrongCredentials(String un, String pwd) {
        System.out.println("try to login with wrong credentials: " + un + " : " + pwd);
        eleUtil.doSendKeys(emailid, un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(login);

        String errorMesg = eleUtil.doGetText(loginErrorMesg);
        System.out.println(errorMesg);
        if(errorMesg.contains(Constants.LOGIN_ERROR_MESSG)) {
            System.out.println("login is not done....");
            return false;
        }
        return true;
    }

    @Step("Navigating to registration page")
    public RegisterPage goToRegPage(){
        eleUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }
}
