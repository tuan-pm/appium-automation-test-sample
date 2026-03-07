package com.android.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utils.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AndroidMobileBaseTest {
    protected AppiumDriver driver;
    private AppiumDriverLocalService service;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String deviceName = ConfigReader.getProperty("device.name");
        String osVersion = ConfigReader.getProperty("os.version");

        // Start Appium server programmatically with ANDROID_HOME set
        Map<String, String> env = new HashMap<>(System.getenv());
        env.put("ANDROID_HOME", "/Users/mabu2/Library/Android/sdk");
        env.put("JAVA_HOME", System.getProperty("java.home"));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformVersion(osVersion);
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
