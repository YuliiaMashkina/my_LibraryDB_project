package com.library.step_defs;

import com.library.utilities.ConfigReader;
import com.library.utilities.DB_util;
import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;


public class Hooks {

    @Before ("@ui")
    public void setupDriver(){
        // set up implicit wait or all the browser related set up
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        // maximize browser here if you wanted
        Driver.getDriver().manage().window().maximize();
    }

    @After ("@ui")
    public void tearDown(Scenario scenario){

        //check if the scenario is failed or not to take screenshot
        if(scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot,"image/png","failure_ss");
        }
        Driver.closeBrowser();

    }


    @Before ("@db")
    public void createConnection(){
   DB_util.createConnection(ConfigReader.read("library2.database.url"),
           ConfigReader.read("library2.database.username" ),
           ConfigReader.read("library2.database.password"));

    }

    @After("@db")
    public void dbTearDown() {
        DB_util.destroy();
    }



}
