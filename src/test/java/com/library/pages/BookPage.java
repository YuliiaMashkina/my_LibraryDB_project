package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookPage {
    public BookPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(xpath = "(//table/tbody//td[3])[1]")
    public WebElement bookName;

    @FindBy(xpath = "(//table/tbody//td[4])[1]")
    public WebElement authorName;

    @FindBy(xpath = "(//table/tbody//td[6])[1]")
    public WebElement year;


    //Borrowing Books

    @FindBy(xpath = "//tbody/tr/td/a")
    public WebElement borrowBookBtn;

    @FindBy(xpath = "//tbody/tr/td[2]")
    public List<WebElement> listOfBorrowedBookNamed;

    @FindBy(xpath = "//tbody/tr/td[6]")
    public List<WebElement> listOfisReturned;







    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }


}
