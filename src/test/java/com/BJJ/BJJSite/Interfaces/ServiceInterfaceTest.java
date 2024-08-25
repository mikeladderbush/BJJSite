package com.BJJ.BJJSite.Interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.BJJ.BJJSite.Classes.User;

class ServiceInterfaceTest {

    private TestService testService;
    private ByteArrayOutputStream outContent;

    // A simple class implementing the ServiceInterface for testing purposes
    private static class TestService implements ServiceInterface {
        // No additional implementation needed
    }

    @BeforeEach
    void setUp() {
        testService = new TestService();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGenerateId() {
        // Act
        Long generatedId = testService.generateId();

        // Assert
        assertNotNull(generatedId, "Generated ID should not be null");
        assertTrue(generatedId > 0, "Generated ID should be positive");
    }

    @Test
    void testDisplayInformation() {
        // Arrange
        User user = new User.UserBuilder<>()
                .username("testuser")
                .email("test@example.com")
                .fullName("Test User")
                .buildUser();

        // Act
        testService.displayInformation(user);

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("User ID: " + user.getUserId()));
        assertTrue(output.contains("Full Name: Test User"));
        assertTrue(output.contains("Email: test@example.com"));
        assertTrue(output.contains("Username: testuser"));
    }
}
