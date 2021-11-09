package com.library.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    private Driver(){
    }

    public static WebDriver getDriver(){
        String browserName = ConfigReader.read("browser");
        if (driver==null){
            switch (browserName){
                case " chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                default:
                    driver=null;
                    System.out.println("BrowserName is invald");
            }
            return driver;
        }else{
            return driver;
        }

    }

    public static void closeBrowser(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }



}
