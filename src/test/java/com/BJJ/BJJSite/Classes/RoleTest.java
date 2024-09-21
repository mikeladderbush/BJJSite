package com.BJJ.BJJSite.Classes;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleUser() {
        Role role = Role.USER;
        assertEquals("USER", role.name());
    }

    @Test
    void testRoleAdmin() {
        Role role = Role.ADMIN;
        assertEquals("ADMIN", role.name());
    }

    @Test
    void testGrantedAuthorityForUser() {
        // Convert the Role enum to SimpleGrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + Role.USER.name());
        assertEquals("ROLE_USER", authority.getAuthority());
    }

    @Test
    void testGrantedAuthorityForAdmin() {
        // Convert the Role enum to SimpleGrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + Role.ADMIN.name());
        assertEquals("ROLE_ADMIN", authority.getAuthority());
    }

    @Test
    void testEnumValues() {
        Role[] roles = Role.values();
        assertEquals(2, roles.length);
        assertArrayEquals(new Role[]{Role.USER, Role.ADMIN}, roles);
    }

    @Test
    void testValueOf() {
        Role userRole = Role.valueOf("USER");
        Role adminRole = Role.valueOf("ADMIN");

        assertEquals(Role.USER, userRole);
        assertEquals(Role.ADMIN, adminRole);
    }
}
