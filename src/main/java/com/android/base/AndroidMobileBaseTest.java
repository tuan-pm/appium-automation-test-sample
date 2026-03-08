package com.android.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.utils.AllureReportUtil;
import com.utils.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AndroidMobileBaseTest {
    protected AppiumDriver driver;
    private AppiumDriverLocalService service;

    @BeforeSuite
    public void beforeSuite() {
        AllureReportUtil.generateEnvironmentProperties();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String deviceName = ConfigReader.getProperty("android.device.name");
        String osVersion = ConfigReader.getProperty("android.os.version");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformVersion(osVersion);
        
        String browserStackAppUrl = ConfigReader.getProperty("browserstack.app_url_android");
        String isBrowserStack = ConfigReader.getProperty("browserstack.enabled");
        
        if (browserStackAppUrl != null && !browserStackAppUrl.isEmpty() && "true".equalsIgnoreCase(isBrowserStack)) {
            // BrowserStack configuration
            options.setApp(browserStackAppUrl);
            options.setCapability("bstack:options", new HashMap<String, Object>() {{
                put("userName", ConfigReader.getProperty("browserstack.user"));
                put("accessKey", ConfigReader.getProperty("browserstack.key"));
            }});
            String browserStackServer = ConfigReader.getProperty("browserstack.server");
            driver = new AndroidDriver(new URL(browserStackServer), options);
        } else {
            // Local Appium configuration
            Map<String, String> env = new HashMap<>(System.getenv());
            env.put("ANDROID_HOME", "/Users/mabu2/Library/Android/sdk");
            env.put("JAVA_HOME", System.getProperty("java.home"));
            
            String appPath = System.getProperty("user.dir") + File.separator + ConfigReader.getProperty("local.app_path_android");
            options.setApp(new File(appPath).getAbsolutePath());
            options.setAutomationName("UiAutomator2");
            options.setCapability("appPackage", "com.swaglabsmobileapp");
            options.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
            options.setCapability("appWaitActivity", "com.swaglabsmobileapp.*");
            options.setCapability("autoGrantPermissions", true);
            options.setCapability("appium:noSign", true);
            options.setCapability("appium:ignoreHiddenApiPolicyError", true);
            driver = new AndroidDriver(new URL(ConfigReader.getProperty("local.appium_server")), options);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
