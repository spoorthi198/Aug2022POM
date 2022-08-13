package com.qa.opencart.tests;

import com.qa.opencart.pages.*;
import com.qa.opencart.driverfactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {
    DriverFactory df;
    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    RegisterPage regPage;
    AccountsPage acctpage;
    SearchResultPage searchPage;
    ProductInfoPage productinfo;
    SoftAssert softAssert;
    @BeforeTest
    public void setUp() throws FileNotFoundException {
        df = new DriverFactory();
        prop=df.init_properties();
        driver =df.init_driver(prop);
        loginPage=new LoginPage(driver);
        softAssert=new SoftAssert();

    }
    @AfterTest
    public void tearDown(){
        driver.quit();

    }
}
