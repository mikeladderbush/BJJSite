package com.BJJ.BJJSite.Security.Authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void testNoArgsConstructor() {
        // Test no-args constructor
        RegisterRequest request = new RegisterRequest();
        assertNull(request.getFirstname());
        assertNull(request.getLastname());
        assertNull(request.getEmail());
        assertNull(request.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com", "password123");
        assertEquals("John", request.getFirstname());
        assertEquals("Doe", request.getLastname());
        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        // Test setters and getters
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password123");

        assertEquals("John", request.getFirstname());
        assertEquals("Doe", request.getLastname());
        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testBuilder() {
        // Test builder pattern
        RegisterRequest request = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        assertEquals("John", request.getFirstname());
        assertEquals("Doe", request.getLastname());
        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }
}
