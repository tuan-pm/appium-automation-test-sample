package com.poc.tests.android;

import dev.failsafe.internal.util.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.android.base.AndroidMobileBaseTest;
import com.android.pages.LoginPage;
import com.android.pages.ProductsPage;

public class LoginTest extends AndroidMobileBaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void loginSuccessfully() {
        loginPage.login("standard_user", "secret_sauce");

        Assert.isTrue(productsPage.isDisplayed(), "Is login successfully");
    }
}
