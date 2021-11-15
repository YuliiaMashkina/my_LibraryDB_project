package com.library.step_defs;

import com.library.utilities.DB_util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US4_ {

    String actual = "";
    @When("I execute query to find most popular user")
    public void i_execute_query_to_find_most_popular_user() {
        String query="select full_name, count(*) from book_borrow bb\n" +
                "inner join users u on bb.user_id = u.id\n" +
                "group by full_name\n" +
                "order by 2 desc ;";
        DB_util.runQuery(query);
        actual=DB_util.getFirstRowFirstColumn();
        System.out.println("actual = " + actual);


    }
    @Then("verify {string} is the user who reads the most")
    public void verify_is_the_user_who_reads_the_most(String username) {
        Assert.assertEquals(username,actual);
    }

}
