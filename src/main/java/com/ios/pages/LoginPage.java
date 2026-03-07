package com.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginPage extends BasePage {
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        if (username != null) {
            driver.findElement(usernameInput).sendKeys(username);
        }
        if (password != null) {
            driver.findElement(passwordInput).sendKeys(password);
        }
        driver.findElement(loginButton).click();
    }

    public boolean isDisplayed() {
        return driver.findElement(usernameInput).isDisplayed();
    }

    public String errorMessage() {
        return driver.findElement(errorMessageLabel).getText();
    }

    By usernameInput = AppiumBy.accessibilityId("test-Username");
    By passwordInput = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMessageLabel = AppiumBy.accessibilityId("test-Error message");
}
