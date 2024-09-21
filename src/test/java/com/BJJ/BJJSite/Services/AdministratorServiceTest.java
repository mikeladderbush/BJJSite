package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Interfaces.ServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorServiceTest {

    private AdministratorService administratorService;

    @BeforeEach
    void setUp() {
        administratorService = new AdministratorService();
    }

    @Test
    void testGenerateId() {
        // Generate an ID using the AdministratorService
        Integer id1 = administratorService.generateId();
        Integer id2 = administratorService.generateId();

        // Ensure that the IDs are positive and unique
        assertNotNull(id1);
        assertTrue(id1 > 0);
        assertNotNull(id2);
        assertTrue(id2 > 0);

        // Verify that each generated ID is unique
        assertNotEquals(id1, id2);
    }

}
