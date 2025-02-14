package com.automation;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WireMockExampleTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {
        // Start WireMock on port 8080
        wireMockServer = new WireMockServer(8087);
        wireMockServer.start();

        // Mock an API response
        wireMockServer.stubFor(get(urlEqualTo("/api/user/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john@example.com\" }")));
    }

    @Test
    void testMockedApiCall() throws IOException {
        String response = ApiClient.makeGetRequest("http://localhost:8087/api/user/1");

        assertTrue(response.contains("John Doe"));
        assertTrue(response.contains("john@example.com"));
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }
}
