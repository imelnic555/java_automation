package com.automation.framework.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import com.automation.framework.config.TestConfig;



@RunWith(Cucumber.class)
@ContextConfiguration(classes = TestConfig.class)

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automation.framework.steps"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunner {
}