package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void testBuilderPattern() {
        User builtUser = User.builder()
                .firstname("Alice")
                .lastname("Wonderland")
                .username("alice")
                .password("alice123")
                .email("alice@example.com")
                .enabled(true)
                .build();

        final Set<String> roles = new HashSet<>();
        roles.add("USER");

        builtUser.setRoles(roles);
        assertEquals("Alice", builtUser.getFirstname());
        assertEquals("Wonderland", builtUser.getLastname());
        assertEquals("alice", builtUser.getUsername());
        assertEquals("alice123", builtUser.getPassword());
        assertEquals("alice@example.com", builtUser.getEmail());
        assertTrue(builtUser.isEnabled());
        assertEquals("ROLE_USER", builtUser.getAuthorities().iterator().next().getAuthority());
    }

}
