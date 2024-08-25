package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User.UserBuilder<?> builder;

    @BeforeEach
    void setUp() {
        builder = new User.UserBuilder<>()
                .fullName("John Doe")
                .username("johndoe")
                .password("password123")
                .email("johndoe@example.com")
                .phone("555-1234")
                .address("123 Main St")
                .sex("Male")
                .dob("01/01/1990")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .createdAt(new Date())
                .updatedAt(new Date())
                .authorities(new ArrayList<>());
    }

    @Test
    void testUserCreation() {
        User user = builder.buildUser();

        assertNotNull(user);
        assertEquals("John Doe", user.getFullName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("555-1234", user.getPhone());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("Male", user.getSex());
        assertEquals("01/01/1990", user.getDob());
        assertTrue(user.getAccountNonExpired());
        assertTrue(user.getAccountNonLocked());
        assertTrue(user.getCredentialsNonExpired());
        assertTrue(user.getEnabled());
    }

    @Test
    void testSettersAndGetters() {
        User user = builder.buildUser();

        user.setFullName("Jane Doe");
        user.setUsername("janedoe");
        user.setPassword("newpassword");
        user.setEmail("janedoe@example.com");
        user.setPhone("555-5678");
        user.setAddress("456 Elm St");
        user.setSex("Female");
        user.setDob("02/02/1992");
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);
        user.setEnabled(false);

        assertEquals("Jane Doe", user.getFullName());
        assertEquals("janedoe", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals("janedoe@example.com", user.getEmail());
        assertEquals("555-5678", user.getPhone());
        assertEquals("456 Elm St", user.getAddress());
        assertEquals("Female", user.getSex());
        assertEquals("02/02/1992", user.getDob());
        assertTrue(!user.getAccountNonExpired());
        assertTrue(!user.getAccountNonLocked());
        assertTrue(!user.getCredentialsNonExpired());
        assertTrue(!user.getEnabled());
    }

    @Test
    void testAddPaymentOption() {
        User user = builder.buildUser();
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Visa")
                .cardNumber("4111111111111111")
                .expirationDate("12/25")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        user.addPaymentOption(paymentOption);

        assertEquals(1, user.getPaymentOptions().size());
        assertEquals("Visa", user.getPaymentOptions().get(0).getName());
    }

    @Test
    void testAddAuthority() {
        User user = builder.buildUser();
        Role role = new Role("ROLE_USER");

        user.addGrantedAuthority(role);

        assertEquals(1, user.getAuthorities().size());
        assertEquals("ROLE_USER", ((Role) user.getAuthorities().iterator().next()).getAuthority());
    }
}
