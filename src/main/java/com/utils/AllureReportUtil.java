package com.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureReportUtil {

    public static void generateEnvironmentProperties() {
        Properties properties = new Properties();
        
        // Add environment information
        properties.setProperty("OS", System.getProperty("os.name"));
        properties.setProperty("Java version", System.getProperty("java.version"));
        
        String configFile = System.getProperty("config.file", "android.properties");
        properties.setProperty("Config File", configFile);
        
        // Add mobile specific environment info if available
        String deviceName = ConfigReader.getProperty("android.device.name");
        if (deviceName != null) {
            properties.setProperty("Android Device", deviceName);
            properties.setProperty("Android Version", ConfigReader.getProperty("android.os.version"));
        }
        
        String iOSDeviceName = ConfigReader.getProperty("ios.device.name");
        if (iOSDeviceName != null) {
            properties.setProperty("iOS Device", iOSDeviceName);
            properties.setProperty("iOS Version", ConfigReader.getProperty("ios.os.version"));
        }

        try {
            String allureResultsPath = "target/allure-results";
            java.io.File directory = new java.io.File(allureResultsPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(allureResultsPath + "/environment.properties");
            properties.store(fileOutputStream, "Allure Environment Properties");
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println("Could not generate Allure environment.properties: " + e.getMessage());
        }
    }
}
