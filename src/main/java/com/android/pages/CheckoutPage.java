package com.android.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

import org.openqa.selenium.By;

import com.android.common.navigation.Scroll;
import com.android.common.navigation.Wait;

public class CheckoutPage extends BasePage {
    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void checkoutWith(String firstName, String lastName, String postalCode){
            Wait.forDisplayed(driver, firstNameInput, Duration.ofSeconds(10));
            driver.findElement(firstNameInput).sendKeys(firstName);
            driver.findElement(lastNameInput).sendKeys(lastName);
            driver.findElement(postalCodeInput).sendKeys(postalCode);
            driver.findElement(continueButton).click();

            Scroll.downToElement(driver,finishButton,10).click();
    }

    public boolean isCheckoutCompleted(){
        Wait.forDisplayed(driver, backHomeButton, Duration.ofSeconds(10));
        return driver.findElement(backHomeButton).isDisplayed();
    }

    //Checkout: Information
    By firstNameInput = AppiumBy.accessibilityId("test-First Name");
    By lastNameInput = AppiumBy.accessibilityId("test-Last Name");
    By postalCodeInput = AppiumBy.accessibilityId("test-Zip/Postal Code");
    By continueButton = AppiumBy.accessibilityId("test-CONTINUE");
    //Checkout: Overview
    By finishButton = AppiumBy.accessibilityId("test-FINISH");
    //Checkout: Completed
    By backHomeButton = AppiumBy.accessibilityId("test-BACK HOME");

}
