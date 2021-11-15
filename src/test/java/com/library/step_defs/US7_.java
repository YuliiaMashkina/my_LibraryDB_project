package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.CommonAreaPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.DB_util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class US7_ {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    CommonAreaPage areaPage = new CommonAreaPage();

    @Given("I login as a {string}")
    public void i_login_as_a(String userType) {
        loginPage.login(userType);
    }

    String theBookName;
    @Given("I search book name called {string}")
    public void i_search_book_name_called(String bookName) {
        theBookName= bookName;
        bookPage.search.sendKeys(theBookName);
    }

    @When("I click Borrow Book")
    public void i_click_borrow_book() {
        BrowserUtil.waitFor(2);
        bookPage.borrowBookBtn.click();
    }

    List<String> uiBorrowedBooks = new ArrayList<>();
    List<String> isReturned = new ArrayList<>();
    List <String> notReturnedBooks = new ArrayList<>();
    //Map<String, String> expected = new LinkedHashMap<>();
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String borrowedBookPage ) {
        areaPage.navigateModule(borrowedBookPage);
        BrowserUtil.waitFor(2);
        uiBorrowedBooks= BrowserUtil.getElementsText(bookPage.listOfBorrowedBookNamed);
        isReturned= BrowserUtil.getElementsText(bookPage.listOfisReturned);
        /*for (int i = 0; i < uiBorrowedBooks.size(); i++) {
            expected.put(uiBorrowedBooks.get(i), isReturned.get(i));
        }*/
        //System.out.println("uiBorrowedBooks = " + uiBorrowedBooks);
        for (int i = 0; i < isReturned.size(); i++) {
            if (isReturned.get(i).equals("NOT RETURNED")){
                notReturnedBooks.add(uiBorrowedBooks.get(i));
            }
        }
        //System.out.println("notReturnedBooks = " + notReturnedBooks);

        Assert.assertTrue(notReturnedBooks.contains(theBookName));
    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        String query = "select full_name, name, is_returned from users\n" +
                "inner join book_borrow bb on users.id = bb.user_id\n" +
                "inner join books b on bb.book_id = b.id where full_name='Test Student 94' having is_returned=0";
        DB_util.runQuery(query);
        List<String> columnAsList = DB_util.getColumnDataAsList(2);
        System.out.println("columnAsList = " + columnAsList);
        Assert.assertTrue(columnAsList.contains(theBookName));


    }
}
