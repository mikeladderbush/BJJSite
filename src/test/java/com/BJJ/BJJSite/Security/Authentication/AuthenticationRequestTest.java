package com.BJJ.BJJSite.Security.Authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    @Test
    void testNoArgsConstructor() {
        // Test no-args constructor
        AuthenticationRequest request = new AuthenticationRequest();
        assertNull(request.getEmail());
        assertNull(request.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        AuthenticationRequest request = new AuthenticationRequest("john.doe@example.com", "password123");
        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        // Test setters and getters
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password123");

        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testBuilder() {
        // Test builder pattern
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("john.doe@example.com")
                .password("password123")
                .build();

        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }
}
