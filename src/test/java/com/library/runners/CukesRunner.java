package com.library.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue="com/library/step_defs",
        publish = true,//it will give you a link to local html report
        plugin = {"pretty", "html:target/cucumber.html",
        "rerun:target/rerun.txt",
        "me.jvt.cucumber.report.PrettyReports:target"
        },
        dryRun = false,
        tags="@us02"
)

public class CukesRunner {

}
