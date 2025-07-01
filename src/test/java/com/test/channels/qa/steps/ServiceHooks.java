package com.test.channels.qa.steps;

import static com.test.channels.qa.framework.appdriver.DriverWrapper.quitDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServiceHooks {

    @Before
    public void before(Scenario scenario) {
        log.info("Starting scenario {}", scenario.getName());
    }

    @After
    public void afterTest(Scenario scenario) {
        log.info("Finishing scenario {}", scenario.getName());
        quitDriver();
    }
}
