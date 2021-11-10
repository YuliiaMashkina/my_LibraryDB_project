package com.library.step_defs;

import com.library.pages.CommonAreaPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.ConfigReader;
import com.library.utilities.DB_util;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2_ {

    LoginPage loginPage = new LoginPage();
    CommonAreaPage commonAreaPage = new CommonAreaPage();

    int borrowedBooksActual;

    @Given("I am in the homepage of library app")
    public void i_am_in_the_homepage_of_library_app() {
        loginPage.login("librarian");
    }

    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {
        BrowserUtil.waitFor(2);
        borrowedBooksActual = BrowserUtil.getIntFromText(commonAreaPage.borrowedBooks);
        //System.out.println("borrowedBooks = " + borrowedBooks);

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        DB_util.runQuery("Select * from book_borrow where returned_date is NULL");
        int booksBorrowedExpected = DB_util.getRowCount();
        System.out.println("DBbooksBorrowed = " + booksBorrowedExpected);

        Assert.assertEquals(booksBorrowedExpected, borrowedBooksActual);



    }

}
