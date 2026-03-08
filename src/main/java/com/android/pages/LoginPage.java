package com.android.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import com.android.common.navigation.Wait;

import java.time.Duration;

public class LoginPage extends BasePage{
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Login as {username} with password {password}")
    public void login(String username, String password){
        if(username!=null){
            Wait.forDisplayed(driver,usernameInput, Duration.ofSeconds(10)).sendKeys(username);
        }
        if(password!=null){
            driver.findElement(passwordInput).sendKeys(password);
        }
        driver.findElement(loginButton).click();
    }

    @Step("Check if login page is displayed")
    public boolean isDisplayed(){
        return driver.findElement(usernameInput).isDisplayed();
    }

    @Step("Get error message")
    public String errorMessage(){
        return driver.findElement(errorMessageLabel).getText();
    }

    By usernameInput = AppiumBy.accessibilityId("test-Username");
    By passwordInput = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMessageLabel = AppiumBy.accessibilityId("test-Error message");


}
