package com.ios.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utils.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class IOSMobileBaseTest {
    protected AppiumDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String platform = ConfigReader.getProperty("platform");
        String deviceName = ConfigReader.getProperty("ios.device.name");
        String osVersion = ConfigReader.getProperty("ios.os.version");
        
        String browserStackAppUrl = ConfigReader.getProperty("browserstack.app_url_ios");
        String isBrowserStack = ConfigReader.getProperty("browserstack.enabled");
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setPlatformVersion(osVersion);

        if (browserStackAppUrl != null && !browserStackAppUrl.isEmpty() && "true".equalsIgnoreCase(isBrowserStack)) {
            // BrowserStack configuration
            options.setApp(browserStackAppUrl);
            options.setCapability("bstack:options", new HashMap<String, Object>() {{
                put("userName", ConfigReader.getProperty("browserstack.user"));
                put("accessKey", ConfigReader.getProperty("browserstack.key"));
            }});
            String browserStackServer = ConfigReader.getProperty("browserstack.server");
            driver = new AndroidDriver(new URL(browserStackServer), options);
        } else if (platform.equalsIgnoreCase("ios")) {
            options.setDeviceName(deviceName);
            options.setPlatformVersion(osVersion);
            String appPath = System.getProperty("user.dir") + File.separator + ConfigReader.getProperty("local.app_path_ios");
            options.setApp(new File(appPath).getAbsolutePath());
            options.setAutomationName("XCUITest");
            driver = new IOSDriver(new URL(ConfigReader.getProperty("local.appium_server")), options);
        } 
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
