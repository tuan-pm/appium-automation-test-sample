package com.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import com.ios.common.navigation.Scroll;
import com.ios.common.navigation.Wait;

import java.time.Duration;

public class ProductsPage extends BasePage {
    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public void addToCart(String product) {
        Scroll.downToElement(driver, addToCartButton(product), 5).click();
    }

    public CartPage goToCart() {
        Wait.forDisplayed(driver, cartIcon, Duration.ofSeconds(5));
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }

    public boolean isDisplayed() {
        Wait.forDisplayed(driver, sortingIcon, Duration.ofSeconds(5));
        return driver.findElement(sortingIcon).isDisplayed();
    }

    By sortingIcon = AppiumBy.accessibilityId("test-Modal Selector Button");
    
    By addToCartButton(String product) {
        return By.xpath("//XCUIElementTypeStaticText[@name='" + product + "']/following-sibling::XCUIElementTypeOther[@name='test-ADD TO CART']");
    }

    By cartIcon = AppiumBy.accessibilityId("test-Cart");
}
