package com.automation.tests;

import com.automation.appium.AppiumDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.automation.pages.LoginPage;

public class LoginTest {
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        AppiumDriverManager.getDriver();
        loginPage = new LoginPage(AppiumDriverManager.getDriver());
    }

    @Test
    public void testValidLogin() {
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();
    }

    @AfterClass
    public void tearDown() {
        AppiumDriverManager.quitDriver();
    }
}