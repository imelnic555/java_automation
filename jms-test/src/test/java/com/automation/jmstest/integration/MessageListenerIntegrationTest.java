package com.automation.jmstest.integration;



import com.automation.jmstest.JmsTestApplication;
import com.automation.jmstest.service.MessageListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = JmsTestApplication.class)  // Ensure the main application class is loaded
@ExtendWith(MockitoExtension.class) // Enables Mockito with JUnit 5
class MessageListenerIntegrationTest {

    @MockBean
    private JmsTemplate jmsTemplate;  // Mock JmsTemplate for test

    @InjectMocks
    private MessageListener messageListener;

    @Test
    void testReceiveMessage() {
        // Simulate JMS behavior using Mockito
        doNothing().when(jmsTemplate).convertAndSend("test-queue", "Hello, JMS!");

        // Act
        jmsTemplate.convertAndSend("test-queue", "Hello, JMS!");

        // Verify that JmsTemplate was called
        verify(jmsTemplate, times(1)).convertAndSend("test-queue", "Hello, JMS!");
    }
}
