package com.automation.framework.utils;

import java.io.File;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

public class WebDriverFactory {
    @Value("${webdriver.chrome.driver}")
    private String chromeDriverPath;

    public WebDriver getDriver() {
        System.out.println("Driver path: " + chromeDriverPath);
        File driverFile = new File(chromeDriverPath);
        if (!driverFile.exists()) {
            throw new RuntimeException("ChromeDriver not found at: " + chromeDriverPath);
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.setExperimentalOption("useAutomationExtension", false);

       // ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        // REMOVE unsupported options
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        return new ChromeDriver(options);
        //return new ChromeDriver(options);
    }
}
