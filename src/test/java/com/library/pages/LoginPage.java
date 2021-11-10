package com.library.pages;

import com.library.utilities.ConfigReader;
import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "inputEmail")
    public WebElement emailBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;

    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksNumber;

    public void login(String userType){
        Driver.getDriver().get(ConfigReader.read("library_url"));
        String username= ConfigReader.read(userType+"_username");
        String password=ConfigReader.read("password");

        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();

    }
}
