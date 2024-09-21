package com.BJJ.BJJSite.Security.Authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationResponseTest {

    @Test
    void testNoArgsConstructor() {
        // Test no-args constructor
        AuthenticationResponse response = new AuthenticationResponse();
        assertNull(response.getToken());
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        AuthenticationResponse response = new AuthenticationResponse("mock-token");
        assertEquals("mock-token", response.getToken());
    }

    @Test
    void testSettersAndGetters() {
        // Test setters and getters
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("mock-token");

        assertEquals("mock-token", response.getToken());
    }

    @Test
    void testBuilder() {
        // Test builder pattern
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("mock-token")
                .build();

        assertEquals("mock-token", response.getToken());
    }
}
