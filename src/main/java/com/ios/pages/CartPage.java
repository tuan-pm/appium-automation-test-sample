package com.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import com.ios.common.navigation.Scroll;

public class CartPage extends BasePage {
    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Go to checkout")
    public CheckoutPage goToCheckout() {
        Scroll.downToElement(driver, checkoutButton, 10).click();
        return new CheckoutPage(driver);
    }

    @Step("Remove {product} from cart")
    public void removeItem(String product) {
        Scroll.downToElement(driver, removeItemButtonByName(product), 10).click();
    }

    @Step("Continue shopping")
    public void continueShopping() {
        Scroll.downToElement(driver, continueShoppingButton).click();
    }

    @Step("Check if {product} is displayed in cart")
    public boolean itemIsDisplayed(String product) {
        return Scroll.downToElement(driver, removeItemButtonByName(product), 5).isDisplayed();
    }

    By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");

    By removeItemButtonByName(String product) {
        return By.xpath("//XCUIElementTypeStaticText[@name='" + product + "']/../../following-sibling::XCUIElementTypeOther[@name='test-REMOVE']");
    }
}
