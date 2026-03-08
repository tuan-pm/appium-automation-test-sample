package com.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import com.ios.common.navigation.Scroll;
import com.ios.common.navigation.Wait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Checkout with first name {firstName}, last name {lastName} and postal code {postalCode}")
    public void checkoutWith(String firstName, String lastName, String postalCode) {
        Wait.forDisplayed(driver, firstNameInput, Duration.ofSeconds(10));
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode + "\n");
        
        driver.findElement(continueButton).click();

        Scroll.downToElement(driver, finishButton, 10).click();
    }

    @Step("Check if checkout is completed")
    public boolean isCheckoutCompleted() {
        Wait.forDisplayed(driver, backHomeButton, Duration.ofSeconds(10));
        return driver.findElement(backHomeButton).isDisplayed();
    }

    By firstNameInput = AppiumBy.accessibilityId("test-First Name");
    By lastNameInput = AppiumBy.accessibilityId("test-Last Name");
    By postalCodeInput = AppiumBy.accessibilityId("test-Zip/Postal Code");
    By continueButton = AppiumBy.accessibilityId("test-CONTINUE");
    By finishButton = AppiumBy.accessibilityId("test-FINISH");
    By backHomeButton = AppiumBy.accessibilityId("test-BACK HOME");
}
