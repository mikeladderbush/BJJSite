package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .enabled(true)
                .role(Role.USER)
                .buildUser();
    }

    @Test
    void testGetFirstname() {
        assertEquals("John", user.getFirstname());
    }

    @Test
    void testSetFirstname() {
        user.setFirstname("Jane");
        assertEquals("Jane", user.getFirstname());
    }

    @Test
    void testSetFirstname_NullValue() {
        assertThrows(IllegalArgumentException.class, () -> user.setFirstname(null));
    }

    @Test
    void testGetLastname() {
        assertEquals("Doe", user.getLastname());
    }

    @Test
    void testSetLastname() {
        user.setLastname("Smith");
        assertEquals("Smith", user.getLastname());
    }

    @Test
    void testSetLastname_NullValue() {
        assertThrows(IllegalArgumentException.class, () -> user.setLastname(null));
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void testSetUsername() {
        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        user.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    void testSetEmail_NullValue() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals("USER", authorities.iterator().next().getAuthority());
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
        assertTrue(user.getEnabled());
    }

    @Test
    void testBuilderPattern() {
        User builtUser = new User.UserBuilder<>()
                .firstname("Alice")
                .lastname("Wonderland")
                .username("alice")
                .password("alice123")
                .email("alice@example.com")
                .enabled(true)
                .role(Role.ADMIN)
                .buildUser();

        assertEquals("Alice", builtUser.getFirstname());
        assertEquals("Wonderland", builtUser.getLastname());
        assertEquals("alice", builtUser.getUsername());
        assertEquals("alice123", builtUser.getPassword());
        assertEquals("alice@example.com", builtUser.getEmail());
        assertTrue(builtUser.getEnabled());
        assertEquals("ADMIN", builtUser.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testAddPaymentOption() {
        PaymentOption paymentOption = mock(PaymentOption.class);
        user.addPaymentOption(paymentOption);
        assertEquals(1, user.getPaymentOptions().size());
        assertTrue(user.getPaymentOptions().contains(paymentOption));
    }
}
