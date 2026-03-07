package com.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import com.ios.common.navigation.Scroll;

public class CartPage extends BasePage {
    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public CheckoutPage goToCheckout() {
        Scroll.downToElement(driver, checkoutButton, 10).click();
        return new CheckoutPage(driver);
    }

    public void removeItem(String product) {
        Scroll.downToElement(driver, removeItemButtonByName(product), 10).click();
    }

    public void continueShopping() {
        Scroll.downToElement(driver, continueShoppingButton).click();
    }

    public boolean itemIsDisplayed(String product) {
        return Scroll.downToElement(driver, removeItemButtonByName(product), 5).isDisplayed();
    }

    By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");

    By removeItemButtonByName(String product) {
        return By.xpath("//XCUIElementTypeStaticText[@name='" + product + "']/../../following-sibling::XCUIElementTypeOther[@name='test-REMOVE']");
    }
}
