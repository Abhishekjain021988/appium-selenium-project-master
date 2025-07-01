package com.test.channels.qa.framework.utils;

import com.test.channels.qa.framework.components.enums.LocatorType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.clipboard.HasClipboard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

/**
 * Common Test Utilities
 *
 * @author abhishekjain
 */
@Log4j2
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {

    public static final String SCREENSHOT_PATH = "/screenshots"; //NOSONAR
    private static final String SCREENSHOT_PREFIX = "Screenshot_";
    private static final String SCREENSHOT_EXT = ".png";

    /**
     * Fail the test and capture a screenshot
     *
     * @param driver - the current driver
     */
    public static void failWithScreenshot(WebDriver driver) {
        failWithScreenshot(null, null, driver);
    }

    /**
     * Fail the test and capture a screenshot
     *
     * @param description - the failure description
     * @param driver      - the current driver
     */
    public static void failWithScreenshot(String description, WebDriver driver) {
        failWithScreenshot(description, null, driver);
    }

    /**
     * Fail the test and capture a screenshot
     *
     * @param description    - the failure description
     * @param screenshotName - the name of the screenshot
     * @param driver         - the current driver
     */
    public static void failWithScreenshot(String description, String screenshotName, WebDriver driver) {

        try {
            if (StringUtils.isNotBlank(screenshotName)) {

                captureScreenShot(screenshotName, driver);
            } else {
                captureScreenShot(driver);
            }
        } catch (IOException e) {
            log.error("Failed to capture screenshot", e);
        }

        if (StringUtils.isNotBlank(description)) {
            Assert.fail(description);
        } else {
            Assert.fail();
        }
    }

    /**
     * Fail the test and capture a screenshot
     *
     * @param e      - the exception thrown during the test
     * @param driver - the current driver
     */
    public static void failWithScreenshot(Exception e, WebDriver driver) {
        log.error("Failing test due to exception", e);
        try {
            captureScreenShot(driver);
        } catch (IOException ioe) {
            log.error("Failed to capture screenshot", ioe);
        }
        Assert.fail(e.getMessage());
    }

    /**
     * Capture a screenshot without failing the test
     *
     * @param driver - the current driver
     * @throws IOException - if an problem occurs
     */
    public static void captureScreenShot(WebDriver driver) throws IOException {
        captureScreenShot(SCREENSHOT_PREFIX + System.currentTimeMillis(), driver);
    }

    /**
     * Capture a screenshot without failing the test
     *
     * @param screenshotName - the name of the screenshot
     * @param driver         - the current driver
     * @throws IOException - if an problem occurs
     */
    public static void captureScreenShot(String screenshotName, WebDriver driver) throws IOException {
        Validate.notEmpty(screenshotName);
        URL path = TestUtil.class.getResource(SCREENSHOT_PATH);
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File screenshot = scrShot.getScreenshotAs(OutputType.FILE);
        String screenshotPath = path.toString() + '/' + screenshotName + SCREENSHOT_EXT;
        FileUtils.copyFile(screenshot, new File(screenshotPath));
        log.info("Captured screenshot {}", screenshotPath);
    }

    /**
     * Hide the keyboard
     *
     * @param driver - the current driver
     */
    public static void hideKeyboard(AppiumDriver driver) {
        driver.hideKeyboard();
    }

    /**
     * Enter the keyboard
     *
     * @param driver - the current driver
     */
    public static void enterKeyboard(AppiumDriver driver) {
        driver.getKeyboard().sendKeys(Keys.ENTER);
    }

    /**
     * Return whether Alert is displayed or not
     *
     * @param driver - the current driver
     */
    public static boolean isAlertPresent(WebDriver driver) {
        boolean alertIsPresent = false;
        try {
            driver.switchTo().alert();
            alertIsPresent = true;
        } catch (Exception ex) {
            alertIsPresent = false;
        }
        return alertIsPresent;
    }

    /**
     * accept alert
     *
     * @param driver - the current driver
     */
    public static void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }


    /**
     * This will check whether element is displayed on UI or not
     *
     * @param element - element on which to perform the action
     * @return true if the element is displayed, false otherwise
     */
    public static boolean isElementDisplayed(WebElement element) {
        WaitUtil.pause(500);
        boolean isPresent = false;
        try {
            element.isDisplayed();
            isPresent = true;
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent && element.isDisplayed();
    }

    /**
     * This function returns the WebElement which is the child of the parent element with xpath
     * @param parentElement - element for which child has to be found
     * @param childXpath - xpath of the child element
     * @return - returns the child element
     */
    public static WebElement findChildElement(WebElement parentElement, String childXpath) {
        return parentElement.findElement(By.xpath(childXpath));
    }

    /**
     * This function returns the List of WebElement based on the LocatorType and value
     * @param locatorType - LocatorType to find the element Eg: ID, XPATH
     * @param value - expression value
     * @return - returns the List having WebElements
     */
    public static List findElements(AppiumDriver driver, LocatorType locatorType, String value) {
        return driver.findElements(locatorType.getLocatorType(), value);
    }

    public static List findElements(WebDriver driver, LocatorType locatorType, String value) {
        return driver.findElements(locatorType.getByLocatorType(value));
    }

    /**
     * This function returns WebElement based on the LocatorType and value
     * @param locatorType - LocatorType to find the element Eg: ID, XPATH
     * @param value - expression value
     * @return - returns element
     */
    public static WebElement findElement(AppiumDriver driver, LocatorType locatorType, String value) {
        return driver.findElement(locatorType.getLocatorType(), value);
    }

    public static WebElement findElement(WebDriver driver, LocatorType locatorType, String value) {
        return driver.findElement(locatorType.getByLocatorType(value));
    }

    /**
     * This function set the value of clipboard
     * @param driver - the current driver
     * @param value - value to be set
     */
    public static void setClipboardText(AppiumDriver driver, String value) {
        ((HasClipboard) driver).setClipboardText(value);
    }

    /**
     * This function minimise the app in background for specific duration
     * @param driver - the current driver
     * @param durationInSecs - duration in seconds
     */
    public static void runAppInBackground(AppiumDriver driver, int durationInSecs) {
        driver.runAppInBackground(Duration.ofSeconds(durationInSecs));
    }

    /**
     * Logout from the mobile app
     * curl -X POST \
          https://onlineuat..com/IB/api/CustomerLogin/logout \
          -H 'cache-control: no-cache' \
          -H 'content-type: application/x-www-form-urlencoded' \
          -d username=06
     */
    public static void logout(String username) {
        log.info("Logging out user {}", username);

        try {

            HttpResponse response = Request.Post(PropertyUtil.get("uat.url.logout"))
                    .bodyForm(Form.form().add("username", username).build())
                    .addHeader("cache-control", "no-cache")
                    .connectTimeout(5000)
                    .socketTimeout(5000)
                    .execute().returnResponse();

            log.info("Got response {} from logout request", response); 
            
        } catch(Exception e) {
            log.error("Failed to logout user {}", username, e);
        }
    }
}
