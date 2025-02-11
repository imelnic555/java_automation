package com.automation.jmstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.automation.jmstest.service") // Ensure this matches your package structure
public class JmsTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JmsTestApplication.class, args);
    }
}
