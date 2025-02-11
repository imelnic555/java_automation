package com.automation.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.url}")
    private String appUrl;

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    public String getAppUrl() {
        return appUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
