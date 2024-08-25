package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.BJJ.BJJSite.Enums.PayBasis;

class EmployeeTest {

    private Employee.EmployeeBuilder<?> builder;

    @BeforeEach
    void setUp() {
        builder = new Employee.EmployeeBuilder<>()
                .fullName("Jane Doe")
                .socialSecurityNumber("123-45-6789")
                .position("Software Engineer")
                .payrate(75000.0)
                .paybasis(PayBasis.SALARY)
                .baseEarnings(75000.0);
    }

    @Test
    void testEmployeeCreation() {
        Employee employee = builder.buildUser();

        assertNotNull(employee);
        assertEquals("Jane Doe", employee.getFullName());
        assertEquals("123-45-6789", employee.getSocialSecurityNumber());
        assertEquals("Software Engineer", employee.getPosition());
        assertEquals(75000.0, employee.getPayrate());
        assertEquals(PayBasis.SALARY, employee.getPaybasis());
        assertEquals(75000.0, employee.getBaseEarnings());
    }

    @Test
    void testDefaultValues() {
        Employee defaultEmployee = new Employee.EmployeeBuilder<>()
                .fullName("John Doe")
                .buildUser();

        assertNotNull(defaultEmployee);
        assertEquals("000-00-0000", defaultEmployee.getSocialSecurityNumber());
        assertEquals("DEFAULT_HIRE", defaultEmployee.getPosition());
        assertEquals(0.0, defaultEmployee.getPayrate());
        assertEquals(PayBasis.VOLUNTEER, defaultEmployee.getPaybasis());
        assertEquals(0.0, defaultEmployee.getBaseEarnings());
    }

    @Test
    void testCustomPayBasis() {
        Employee hourlyEmployee = builder.paybasis(PayBasis.HOURLY).buildUser();

        assertNotNull(hourlyEmployee);
        assertEquals(PayBasis.HOURLY, hourlyEmployee.getPaybasis());
    }
}
