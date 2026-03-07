package com.poc.tests.android;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.android.base.AndroidMobileBaseTest;
import com.android.pages.CartPage;
import com.android.pages.CheckoutPage;
import com.android.pages.LoginPage;
import com.android.pages.ProductsPage;

public class OrderingProductsTest extends AndroidMobileBaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void loginWithCredential() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void orderingProductsSuccessfully() {
        // And I finish checkout products in my cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.goToCart();
        cartPage.gotoCheckout();
        checkoutPage.checkoutWith("toronto", "toronto", "10000");

        // Then I checkout my products successfully
        Assert.assertTrue(checkoutPage.isCheckoutCompleted(),
                "Checkout should be completed successfully");
    }
}
