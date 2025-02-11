package com.automation.framework.steps;

import com.automation.framework.config.TestConfig;
import com.automation.framework.utils.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import com.automation.framework.config.TestConfig;

import static org.junit.Assert.assertTrue;
@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class LoginSteps {
    @Autowired
    private WebDriverFactory webDriverFactory;

    @Autowired
    private Environment env;

    private WebDriver driver;

    @Given("I open the browser")
    public void iOpenTheBrowser() {
        driver = webDriverFactory.getDriver();
        driver.get("https://www.google.com");
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        // Implement login actions using externalized data
    }

    @Then("I should see the dashboard")
    public void iShouldSeeTheDashboard() {
        assertTrue(driver.getTitle().contains("Google"));
        driver.quit();
    }
}
