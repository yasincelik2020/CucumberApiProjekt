package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {//report plugins
                "pretty",//prints colored logs to the console
                "html:target/reports/html-reports/default-cucumber-reports.html",//plugin used to generate reports in html format
                "rerun:target/failedRerun.txt",
        },
        features = "src/test/resources/features",
        glue = {"stepdefinitions","base_urls"}, //Java kodlarinin calisacagi package'lar.Bunlar calisir.
        tags = "@Contact",
        dryRun = false // dryRun = true yapildiginda test calismaz, sadece eksik step definitionlar tespit edilir.

)
public class Runner {}


/*
                "junit:target/reports/xml-reports/cucumber1.xml",//plugin used to generate reports in xml format
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" //For Spark report
 */