package com.test.channels.qa.framework.appdriver;

import static com.test.channels.qa.framework.utils.PropertyUtil.get;
import static com.test.channels.qa.framework.utils.PropertyUtil.getLong;

import com.test.channels.qa.framework.utils.PropertyUtil;
import com.test.channels.qa.framework.components.enums.Platform;
import com.test.channels.qa.framework.exception.FrameworkException;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Log4j2
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverWrapper {

    private static volatile MobileDriver driver; //NOSONAR

    private static DesiredCapabilities getIosCapabilities(String appPath) {
        log.info("Initializing iOS capabilities for app {}", appPath);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, PropertyUtil.get("ios.platform.name"));
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, PropertyUtil.get("ios.platform.version"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyUtil.get("ios.device.name"));
        cap.setCapability(MobileCapabilityType.APP, appPath);
        cap.setCapability(MobileCapabilityType.NO_RESET, PropertyUtil.get("ios.no.reset"));
        cap.setCapability(IOSMobileCapabilityType.CONNECT_HARDWARE_KEYBOARD, true);
        return cap;
    }

    private static DesiredCapabilities getAndroidCapabilities(String appPath) {
        log.info("Initializing Android capabilities for app {}", appPath);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, PropertyUtil.get("android.platform.name"));
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, PropertyUtil.get("android.platform.version"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyUtil.get("android.device.name"));
        cap.setCapability(MobileCapabilityType.APP, appPath);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, PropertyUtil.get("android.automation.name"));
        cap.setCapability("autoGrantPermissions", true);
        return cap;
    }

    private static void initDriver(Platform browser) {

//        String cmdAppPath = System.getProperty("app.path");
        String cmdAppPath = PropertyUtil.get("android.app.path");
        DesiredCapabilities capabilities = null;
        log.info("Initializing driver for browser {}", browser);

        try {
            URL appiumUrl = new URL(PropertyUtil.get("appium.driver.url"));

            if (Platform.ANDROID == browser) {
                driver = new AndroidDriver<MobileElement>(appiumUrl, getAndroidCapabilities(cmdAppPath));
            } else if (Platform.IOS == browser){
                driver = new IOSDriver<MobileElement>(appiumUrl, getIosCapabilities(cmdAppPath));
            } else {
                throw new FrameworkException("Unknown browser " + browser);
            }

            driver.manage().timeouts().implicitlyWait(PropertyUtil.getLong("appium.driver.implicit.timeout"),
                    TimeUnit.SECONDS);
//            driver.navigate().to(PropertyUtil.get("test.application.url"));

        } catch (MalformedURLException e) {
            log.error("Failed to initialize the WEB Driver", e);
            throw new FrameworkException(e);
        }
        log.info("Successfully initialized driver {} for browser {}", driver, browser);
    }

    /**
     * TO DO wire up the values coming from the commandline
     * @return
     */
    public static Platform getActivePlatform() {
        String platform = PropertyUtil.get("default.platform.name");
        return Platform.fromString(platform);
    }

    /**
     * Get the Appium driver. We need to add support for parallel execution and multiple drivers
     * @return the initialized Appium driver
     */
    public static MobileDriver getDriver() {
        if(driver == null) {
            synchronized(DriverWrapper.class) {

                if(driver == null) {

                    String cmdPlatform = System.getProperty("platform.name");

                    Platform platform = null;

                    if(StringUtils.isBlank(cmdPlatform)) {
                        platform = getActivePlatform();
                    } else {
                        platform = Platform.fromString(cmdPlatform);
                    }

                    if(Platform.ANDROID == platform) {
                        launchEmulator();
                    }

                    initDriver(platform);
                }
            }
        }

        return (MobileDriver) driver;
    }

    /**
     * Quit the appium driver and close any windows
     */
    public static void quitDriver() {
        if(driver != null) {
            synchronized(DriverWrapper.class) {
                if(driver != null) {
                    log.info("Quiting driver {}", driver);
                    driver.quit();
                    driver = null;
                }
            }
        }
    }

    /**
     * Launch the Android virtual device
     */
    public static void launchEmulator() {
        try {
//            Runtime.getRuntime().exec("emulator -avd " + PropertyUtil.get("android.device.name"));
            String sdkRoot = System.getenv("ANDROID_HOME");
            String emulatorBinary = sdkRoot + "/emulator/emulator";  // This is the actual binary

            File emulatorFile = new File(emulatorBinary);
            if (!emulatorFile.exists()) {
                throw new IOException("Emulator binary not found at: " + emulatorBinary);
            }
            if (!emulatorFile.canExecute()) {
                // Optional fix: try setting execute permission (only once)
                emulatorFile.setExecutable(true);
            }

            ProcessBuilder pb = new ProcessBuilder(emulatorBinary, "-avd", PropertyUtil.get("android.device.name"), "-netdelay", "none", "-netspeed", "full");
            pb.redirectErrorStream(true);
            Process p = pb.start();
        } catch (IOException e) {
            log.error("Failed to launch the Android emulator", e);
        }
    }

}
