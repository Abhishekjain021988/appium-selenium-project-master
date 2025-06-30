package com.test.channels.qa.framework.report;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author abhishekjain
 *
 */
@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext test) {
        log.info("+Begin test: " + test.getName());
    }

    @Override
    public void onTestStart(ITestResult test) {
        log.info(" Starting test: " + test.getName());
    }

    @Override
    public void onTestSuccess(ITestResult test) {
        log.info(" Test passed: " + test.getName());
    }

    @Override
    public void onTestFailure(ITestResult test) {
        log.info(" Test failed: " + test.getName());
    }

    @Override
    public void onTestSkipped(ITestResult test) {
        log.info(" Test ignored: " + test.getName());
    }

    @Override
    public void onFinish(ITestContext test) {
        log.info("-End test: " + test.getName());
    }

}
