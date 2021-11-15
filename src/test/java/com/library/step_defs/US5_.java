package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.CommonAreaPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.DB_util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class US5_ {

    CommonAreaPage commonAreaPage = new CommonAreaPage();
    BookPage bookPage = new BookPage();

    @When("I navigate to {string} page")
    public void i_navigate_to_page(String moduleName) {
        commonAreaPage.navigateModule(moduleName);
    }
    List <String> actual = new ArrayList();
    List <String> expected = new ArrayList();

    @When("I open a book called {string}")
    public void i_open_a_book_called(String bookName) {
        bookPage.search.sendKeys(bookName);
        BrowserUtil.waitFor(1);
        expected.add(bookPage.bookName.getText());
        expected.add(bookPage.authorName.getText());
        expected.add(bookPage.year.getText());
        System.out.println("expected = " + expected);
    }


    @When("I execute query to get the book information from books table")
    public void i_execute_query_to_get_the_book_information_from_books_table() {
        String query = "select name, author, year from books where name ='Chordeiles minor';";
        DB_util.runQuery(query);
        actual=DB_util.getRowDataAsList(1);
        System.out.println("actual = " + actual);
    }
    @Then("verify book DB and UI information must match")
    public void verify_book_db_and_ui_information_must_match() {
        Assert.assertEquals(expected,actual);

    }
}
