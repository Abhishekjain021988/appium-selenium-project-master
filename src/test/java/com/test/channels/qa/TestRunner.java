package com.test.channels.qa;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * A Cucumber TestNG runner to automate cucumber features
 * For more information
 * <a href="https://github.com/cucumber/cucumber-jvm/tree/master/testng">
 * Cucumber TestNG
 * </a>
 * How to Update the Tags:
 * ORed : tags = {"@Smoke, @Regression"} To add condition for OR operator
 * Anded : tags = {"@Smoke", "@Regression"} To add condition for AND operator
 * Ignore Case : tags = {"~@Smoke", "@Regression"} To ignore a set of test cases
 * use ~ operator
 *
 * @author abhishekjain
 */
@CucumberOptions(
    features = "src/test/java/com/test/channels/qa/features", //"classpath:features"
    tags = "@existingwallet or @onboarding",
    glue = {"com/test/channels/qa/steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html-reports",
        "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
        "rerun:target/cucumber-reports/rerun.txt",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    dryRun = false,
    monochrome = false,
    snippets = SnippetType.CAMELCASE)
//@Listeners(TestListener.class)
@Log4j2
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void beforeTest() {
        log.info("************************** @BeforeTest");
    }

    @AfterTest
    public void afterTest() {
        log.info("************************** @AfterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        log.info("************************** @BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        log.info("************************** @AfterSuite");
    }

    @BeforeMethod
    public void beforeMethod() {
        log.info("************************** @BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        log.info("************************** @AfterMethod");
    }

}
