package com.BJJ.BJJSite.Services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdministratorServiceTest {

    private AdministratorService administratorService;

    @BeforeEach
    void setUp() {
        // Initialize the AdministratorService before each test
        administratorService = new AdministratorService();
    }

    @Test
    void testGenerateId() {
        // Generate an ID
        Long generatedId = administratorService.generateId();
        
        // Assert that the generated ID is not null
        assertNotNull(generatedId, "The generated ID should not be null");
        
        // Assert that the generated ID is positive
        assertTrue(generatedId > 0, "The generated ID should be a positive number");
    }
}
