package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    void testRoleCreationWithAuthority() {
        String authority = "ROLE_ADMIN";
        Role role = new Role(authority);

        assertNotNull(role);
        assertEquals(authority, role.getAuthority());
    }

    @Test
    void testRoleDefaultConstructor() {
        Role role = new Role();
        
        assertNotNull(role);
        // The authority should be null if not set explicitly.
        assertEquals(null, role.getAuthority());
    }

    @Test
    void testSetAndGetAuthority() {
        String authority = "ROLE_USER";
        Role role = new Role();
        role = new Role(authority);

        assertEquals(authority, role.getAuthority());
    }
}
