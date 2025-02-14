package com.automation.pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.example.app:id/username")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.example.app:id/password")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.example.app:id/loginButton")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}