package com.poc.tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ios.base.IOSMobileBaseTest;
import com.ios.pages.LoginPage;
import com.ios.pages.ProductsPage;

public class LoginTest extends IOSMobileBaseTest {

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

        Assert.assertTrue(productsPage.isDisplayed(), "Is login successfully");
    }
}
