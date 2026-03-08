package com.android.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CommonPage extends BasePage{
    public CommonPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Logout from the application")
    public void logout(){
        driver.findElement(menuIcon).click();
        driver.findElement(logoutButton).click();
    }
    By menuIcon = AppiumBy.accessibilityId("test-Menu");
    By logoutButton = By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGOUT\"]");

}
