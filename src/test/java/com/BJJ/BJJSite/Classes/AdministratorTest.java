package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;
import com.BJJ.BJJSite.Enums.PayBasis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdministratorTest {

    private Administrator administrator;

    @BeforeEach
    void setUp() {
        administrator = new Administrator.AdministratorBuilder()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .socialSecurityNumber("123-45-6789")
                .position("Administrator")
                .payrate(100.0)
                .paybasis(PayBasis.SALARY)
                .baseEarnings(150000.0)
                .adminLevel("SUPER_ADMIN")
                .enabled(true)
                .role(Role.ADMIN)
                .buildUser();
    }

    @Test
    void testGetFirstname() {
        assertEquals("John", administrator.getFirstname());
    }

    @Test
    void testGetLastname() {
        assertEquals("Doe", administrator.getLastname());
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe", administrator.getUsername());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", administrator.getEmail());
    }

    @Test
    void testGetSocialSecurityNumber() {
        assertEquals("123-45-6789", administrator.getSocialSecurityNumber());
    }

    @Test
    void testGetPosition() {
        assertEquals("Administrator", administrator.getPosition());
    }

    @Test
    void testGetPayrate() {
        assertEquals(100.0, administrator.getPayrate());
    }

    @Test
    void testGetPaybasis() {
        assertEquals(PayBasis.SALARY, administrator.getPaybasis());
    }

    @Test
    void testGetBaseEarnings() {
        assertEquals(150000.0, administrator.getBaseEarnings());
    }

    @Test
    void testGetAdminLevel() {
        assertEquals("SUPER_ADMIN", administrator.getAdminLevel());
    }

    @Test
    void testBuilderPattern() {
        Administrator newAdmin = new Administrator.AdministratorBuilder()
                .firstname("Alice")
                .lastname("Wonderland")
                .username("alice")
                .password("alice123")
                .email("alice@example.com")
                .socialSecurityNumber("987-65-4321")
                .position("HR Administrator")
                .payrate(90.0)
                .paybasis(PayBasis.HOURLY)
                .baseEarnings(120000.0)
                .adminLevel("HR_ADMIN")
                .enabled(true)
                .role(Role.ADMIN)
                .buildUser();

        assertEquals("Alice", newAdmin.getFirstname());
        assertEquals("Wonderland", newAdmin.getLastname());
        assertEquals("alice", newAdmin.getUsername());
        assertEquals("alice@example.com", newAdmin.getEmail());
        assertEquals("987-65-4321", newAdmin.getSocialSecurityNumber());
        assertEquals("HR Administrator", newAdmin.getPosition());
        assertEquals(90.0, newAdmin.getPayrate());
        assertEquals(PayBasis.HOURLY, newAdmin.getPaybasis());
        assertEquals(120000.0, newAdmin.getBaseEarnings());
        assertEquals("HR_ADMIN", newAdmin.getAdminLevel());
    }
}
