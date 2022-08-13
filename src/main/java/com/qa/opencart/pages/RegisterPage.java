package com.qa.opencart.pages;

import com.qa.opencart.utills.Constants;
import com.qa.opencart.utills.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil eleutil;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        eleutil = new ElementUtil(driver);
    }

    private By registerLink=By.linkText("Register");
    private By logoutlink=By.linkText("Logout");
    private  By firstName = By.id("input-firstname");
    private  By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By tele = By.id("input-telephone");
    private  By password = By.id("input-password");
    private  By confirmPwd = By.id("input-confirm");
    private  By TC=By.name("agree");
    private  By Continue = By.xpath("//input[@type='submit']");

    private  By Success = By.cssSelector("div #content h1");


    public String  getTitle(){
        return eleutil.waitForTitleContains(Constants.REGISTER_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
    }

    public boolean reglinkExist(){
        return eleutil.doIsDisplayed(registerLink);
    }

    public void doClickRegLink(){
        eleutil.doClick(registerLink);
    }
    public void doclicklogout(){
        eleutil.doClick(logoutlink);
    }

  public void doFillTheRegForm(String fn,String ln,String eml,String tel,String pw,String cpw){
   eleutil.doSendKeys(firstName,fn);
   eleutil.doSendKeys(lastName,ln);
   eleutil.doSendKeys(email,eml);
   eleutil.doSendKeys(tele,tel);
   eleutil.doSendKeys(password,pw);
   eleutil.doSendKeys(confirmPwd,cpw);
   eleutil.doClick(TC);
   eleutil.doClick(Continue);
  }


  public  String testAccountCreation(){
      return  eleutil.doGetText(Success);
  }
}
