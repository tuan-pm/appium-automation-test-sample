package com.poc.tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ios.base.IOSMobileBaseTest;
import com.ios.pages.CartPage;
import com.ios.pages.CheckoutPage;
import com.ios.pages.LoginPage;
import com.ios.pages.ProductsPage;

public class OrderingProductsTest extends IOSMobileBaseTest {

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
        productsPage.goToCart();
        cartPage.goToCheckout();
        checkoutPage.checkoutWith("toronto", "toronto", "10000");

        // Then I checkout my products successfully
        Assert.assertTrue(checkoutPage.isCheckoutCompleted(),
                "Checkout should be completed successfully");
    }
}
