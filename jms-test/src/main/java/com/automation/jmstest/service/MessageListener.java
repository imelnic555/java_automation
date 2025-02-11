package com.automation.jmstest.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component  // Ensure this annotation is present!
public class MessageListener {

    private String lastReceivedMessage;

    @JmsListener(destination = "test-queue")
    public void receiveMessage(String message) {
        this.lastReceivedMessage = message;
        System.out.println("Received message: " + message);
    }

    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }
}
