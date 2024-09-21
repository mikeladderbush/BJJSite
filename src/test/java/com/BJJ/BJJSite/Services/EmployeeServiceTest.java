package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Interfaces.ServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void testGenerateId() {
        // Generate two IDs using the EmployeeService
        Integer id1 = employeeService.generateId();
        Integer id2 = employeeService.generateId();

        // Ensure that the IDs are positive and unique
        assertNotNull(id1);
        assertTrue(id1 > 0, "ID should be a positive number");
        assertNotNull(id2);
        assertTrue(id2 > 0, "ID should be a positive number");

        // Ensure that each generated ID is unique
        assertNotEquals(id1, id2, "IDs should be unique");
    }
}
