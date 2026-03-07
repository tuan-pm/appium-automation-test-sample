package com.ios.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utils.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSMobileBaseTest {
    protected AppiumDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String deviceName = ConfigReader.getProperty("device.name");
        String osVersion = ConfigReader.getProperty("os.version");

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setPlatformVersion(osVersion);
        String appPath = System.getProperty("user.dir") + File.separator + ConfigReader.getProperty("local.app_path_ios");
        options.setApp(new File(appPath).getAbsolutePath());
        options.setAutomationName("XCUITest");
        options.setWdaLaunchTimeout(Duration.ofSeconds(120));
        options.setCapability("appium:waitForQuiescence", false);
        driver = new IOSDriver(new URL(ConfigReader.getProperty("local.appium_server")), options);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
