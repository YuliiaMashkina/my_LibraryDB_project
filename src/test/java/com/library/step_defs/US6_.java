package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.DB_util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US6_ {

    BookPage bookPage = new BookPage();
    List<String> expected = new ArrayList<>();
    List<String> actual = new ArrayList<>();
    @When("I take all book categories in webpage")
    public void i_take_all_book_categories_in_webpage() {

        Select select = new Select(bookPage.mainCategoryElement);
        List<WebElement> options = select.getOptions();
        expected = BrowserUtil.getElementsText(options);
        expected.remove(0);
        System.out.println("expected = " + expected);


    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        String query = "select name from book_categories;";
        DB_util.runQuery(query);
        actual=DB_util.getColumnDataAsList(1);
        System.out.println("actual = " + actual);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        Assert.assertEquals(expected, actual);
    }

}
