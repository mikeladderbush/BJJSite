package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdministratorTest {

    private Administrator.AdministratorBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new Administrator.AdministratorBuilder()
                .fullName("John Doe")
                .position("Admin")
                .payrate(100000.0)
                .adminLevel("Super Admin");
    }

    @Test
    void testAdministratorCreation() {
        Administrator admin = builder.buildUser();
        
        assertNotNull(admin);
        assertEquals("John Doe", admin.getFullName());
        assertEquals("Admin", admin.getPosition());
        assertEquals(100000.0, admin.getPayrate());
        assertEquals("Super Admin", admin.getAdminLevel());
    }

    @Test
    void testDefaultAdminLevel() {
        Administrator defaultAdmin = new Administrator.AdministratorBuilder()
                .fullName("Jane Doe")
                .position("Admin")
                .payrate(80000.0)
                .buildUser();
        
        assertNotNull(defaultAdmin);
        assertEquals("DEFAULT ADMIN", defaultAdmin.getAdminLevel());
    }

    @Test
    void testAdminLevelCustomValue() {
        Administrator customAdmin = builder.adminLevel("Custom Admin Level").buildUser();
        
        assertNotNull(customAdmin);
        assertEquals("Custom Admin Level", customAdmin.getAdminLevel());
    }
}
