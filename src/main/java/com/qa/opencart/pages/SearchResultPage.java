package com.qa.opencart.pages;

import com.qa.opencart.utills.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private  WebDriver driver;
    private ElementUtil eleUtil;


    private By productsList = By.cssSelector("div.caption a");
    public SearchResultPage(WebDriver driver){
        this.driver=driver;
        eleUtil = new ElementUtil(driver);

    }

    public int getProductCount(){
        return eleUtil.waitForElementsPreence(productsList, 20).size();
    }

    public ProductInfoPage selectProduct(String productName){
        List<WebElement> ele = eleUtil.waitForElementsPreence(productsList, 80);

        for (WebElement e:ele)
             {
                 String text = e.getText();
                 //System.out.println(text);
                 if(text.equals(productName))
                 {
                     e.click();
                     break;

                 }

        }

            return new ProductInfoPage(driver);
    }
}