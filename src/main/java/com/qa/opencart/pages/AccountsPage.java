package com.qa.opencart.pages;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    ElementUtil elementUtil;

    public AccountsPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);
    }

    private By acctDesc = By.cssSelector("div#content h2");
    private   By searchField = By.name("search");
    private  By searchButton = By.xpath("//span[@class='input-group-btn']/button");
    private  By Logout = By.linkText("Logout");


   public String getTitle(){
     return   elementUtil.waitForTitleContains(Constants.ACCOUNT_PAGE_TITLE,15);

   }

    public List<String> verifyAcctDesc(){
        List<WebElement> desclist = elementUtil.waitForElementsPreence(acctDesc, 10);
        ArrayList<String> acctList = new ArrayList<>();
        for (WebElement e:desclist)
        {
            acctList.add(e.getText());

        }
        return acctList;
    }

 public boolean isSearchFieldexit(){
      return elementUtil.isElementExist(searchField);
 }

 public SearchResultPage doSearch(String productName){
       elementUtil.doSendKeys(searchField,productName);
       elementUtil.doClick(searchButton);
       return new SearchResultPage(driver);

 }

 public boolean islogoutlinkexit(){
      return elementUtil.isElementExist(Logout);
 }

 public void testlogout(){
       if(islogoutlinkexit()){
           elementUtil.doClick(Logout);
       }
 }
}
