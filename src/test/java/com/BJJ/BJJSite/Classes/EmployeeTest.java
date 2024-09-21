package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;

import com.BJJ.BJJSite.Enums.PayBasis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee.EmployeeBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .socialSecurityNumber("123-45-6789")
                .position("Developer")
                .payrate(50.0)
                .paybasis(PayBasis.HOURLY)
                .baseEarnings(100000.0)
                .enabled(true)
                .role(Role.USER)
                .buildUser();
    }

    @Test
    void testGetFirstname() {
        assertEquals("John", employee.getFirstname());
    }

    @Test
    void testGetLastname() {
        assertEquals("Doe", employee.getLastname());
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe", employee.getUsername());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", employee.getEmail());
    }

    @Test
    void testGetSocialSecurityNumber() {
        assertEquals("123-45-6789", employee.getSocialSecurityNumber());
    }

    @Test
    void testGetPosition() {
        assertEquals("Developer", employee.getPosition());
    }

    @Test
    void testGetPayrate() {
        assertEquals(50.0, employee.getPayrate());
    }

    @Test
    void testGetPaybasis() {
        assertEquals(PayBasis.HOURLY, employee.getPaybasis());
    }

    @Test
    void testGetBaseEarnings() {
        assertEquals(100000.0, employee.getBaseEarnings());
    }

    @Test
    void testBuilderPattern() {
        Employee newEmployee = new Employee.EmployeeBuilder<>()
                .firstname("Alice")
                .lastname("Wonderland")
                .username("alice")
                .password("alice123")
                .email("alice@example.com")
                .socialSecurityNumber("987-65-4321")
                .position("Manager")
                .payrate(100.0)
                .paybasis(PayBasis.SALARY)
                .baseEarnings(120000.0)
                .enabled(true)
                .role(Role.ADMIN)
                .buildUser();

        assertEquals("Alice", newEmployee.getFirstname());
        assertEquals("Wonderland", newEmployee.getLastname());
        assertEquals("alice", newEmployee.getUsername());
        assertEquals("alice@example.com", newEmployee.getEmail());
        assertEquals("987-65-4321", newEmployee.getSocialSecurityNumber());
        assertEquals("Manager", newEmployee.getPosition());
        assertEquals(100.0, newEmployee.getPayrate());
        assertEquals(PayBasis.SALARY, newEmployee.getPaybasis());
        assertEquals(120000.0, newEmployee.getBaseEarnings());
        assertTrue(newEmployee.getEnabled());
        assertEquals("ADMIN", newEmployee.getAuthorities().iterator().next().getAuthority());
    }
}
