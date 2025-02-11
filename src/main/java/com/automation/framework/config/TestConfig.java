package com.automation.framework.config;

import com.automation.framework.utils.WebDriverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.automation.framework")
@PropertySource("classpath:application.properties")
public class TestConfig {
    @Bean
    public WebDriverFactory webDriverFactory() {
        return new WebDriverFactory();
    }
}