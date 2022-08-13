package com.qa.opencart.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    public WebDriver driver;
    public  Properties prop;
    public static String highlight;

    public OptionsManager optionsManager;

    /***
     * This method will initialize webdriver
     * @param
     * @return
     */

    public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser");
        System.out.println("browser name is :" + browserName);
        highlight=prop.getProperty("highlight");
        optionsManager=new OptionsManager(prop);
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver(optionsManager.getChromeOptions());
            tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
            tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browserName.equals("safari")) {
            //driver = new SafariDriver();
            tldriver.set(new SafariDriver());
        } else {
            System.out.println("please pass the correct browser name");
        }
        getTLocalDriver().manage().window().fullscreen();
        getTLocalDriver().manage().deleteAllCookies();
        getTLocalDriver().get(prop.getProperty("url"));
     return getTLocalDriver();
    }

    public static synchronized WebDriver  getTLocalDriver(){
        return tldriver.get();
    }
    public Properties init_properties() throws FileNotFoundException {
        prop = new Properties();
        FileInputStream ip = null;

        String envName = System.getProperty("env");

        if(envName==null){
        try {
            ip = new FileInputStream("./src/main/resources/Config/con.properties");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }}
        else {
            System.out.println("you are running on :"+envName);
            switch (envName.toLowerCase()){
                case "qa":
                    ip = new FileInputStream("./src/main/resources/Config/QA.con.properties");
                    break;
                case "stage":
                    ip= new FileInputStream("./src/main/resources/Config/Stage.con.properties");
                    break;
                case "dev":
                    ip= new FileInputStream("./src/main/resources/Config/Dev.con.properties");
                    break;
                default:
                    System.out.println("supply correct env");
                    break;
            }

        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;

    }

    public String getScreenshot() {
        File srcFile=((TakesScreenshot)getTLocalDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }
}