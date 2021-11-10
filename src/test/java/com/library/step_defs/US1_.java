package com.library.step_defs;

import com.library.utilities.DB_util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US1_ {

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_util.createConnection();
    }
    List<String> ids;
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
       DB_util.runQuery("SELECT id from users group by id" );
       ids = DB_util.getColumnDataAsList(1);
       // System.out.println(ids);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        DB_util.runQuery("SELECT distinct id from users group by id" );
        List<String> uniqueIds = DB_util.getColumnDataAsList(1);
        //System.out.println(uniqueIds);
        Assert.assertEquals(ids, uniqueIds);
       // System.out.println("pass");

    }


    List<String> dbColNames;
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_util.runQuery("SELECT * from users" );
        dbColNames=DB_util.getColumnNamesAsList();
        //System.out.println("here is my result "+ colNames);

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String>  actualResult) {
        //System.out.println("actualResult = " + actualResult);
        Assert.assertEquals(dbColNames,actualResult);


    }

}
