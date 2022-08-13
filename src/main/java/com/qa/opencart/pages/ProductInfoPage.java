package com.qa.opencart.pages;

import com.qa.opencart.utills.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil eleutil;

    private By prodHeader = By.xpath("//div[@id='content']//h1");
    private  By img = By.cssSelector("ul.thumbnails img");

    private By metaData= By.cssSelector("div.col-sm-4 ul:nth-of-type(1) li");
    private By productPriceData = By.cssSelector("div.col-sm-4 ul:nth-of-type(2) li");

    private By qty = By.id("input-quantity");
    private By addToCart = By.id("button-cart");

    private Map<String,String> productInfoMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver=driver;
        eleutil=new ElementUtil(driver);
    }

    public String getProductHeader(){
        String text;
        text =eleutil.doGetText(prodHeader);
        System.out.println(text);
        return text;
    }

    public int getProductImgCount(){
        return eleutil.getElements(img).size();
    }

    public  Map<String, String> getProductData(){
        productInfoMap = new LinkedHashMap<String,String>();
        productInfoMap.put("productName",getProductHeader());
        List<WebElement> metaDataList = eleutil.getElements(metaData);
        for (WebElement e:metaDataList) {

            String text =e.getText();
          String[]  strArray= text.split(":");
          String mapKey = strArray[0];
           String metaVal = strArray[1];

        productInfoMap.put(mapKey,metaVal);
        }
    return productInfoMap;
    }

    public Map<String, String> getPricingData(){
        List<WebElement> priceData = eleutil.getElements(productPriceData);
        productInfoMap.put("price",priceData.get(0).getText());
        String[] text = priceData.get(1).getText().split(":");
        productInfoMap.put(text[0],text[1]);
        return productInfoMap;
    }

    public void addToCart(){
        eleutil.doClick(addToCart);
    }
}

