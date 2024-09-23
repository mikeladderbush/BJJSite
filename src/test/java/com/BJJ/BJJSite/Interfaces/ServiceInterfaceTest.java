package com.BJJ.BJJSite.Interfaces;

import com.BJJ.BJJSite.Classes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceInterfaceTest {

    private ServiceInterface serviceInterface;

    @BeforeEach
    void setUp() {
        // Creating a minimal implementation of the interface for testing purposes
        serviceInterface = new ServiceInterface() {
            // Empty implementation since default methods will be tested
        };
    }

    @Test
    void testGenerateId() {
        Integer id1 = serviceInterface.generateId();
        Integer id2 = serviceInterface.generateId();

        // Check that generated IDs are positive and different
        assertNotNull(id1);
        assertNotNull(id2);
        assertTrue(id1 > 0);
        assertTrue(id2 > 0);
        assertNotEquals(id1, id2);  // Random IDs should not be equal
    }

    @Test
    void testDisplayInformation() {
        // Mocking the User class
        User mockUser = mock(User.class);
        when(mockUser.getUserId()).thenReturn(1);
        when(mockUser.getFirstname()).thenReturn("John");
        when(mockUser.getLastname()).thenReturn("Doe");
        when(mockUser.getEmail()).thenReturn("john.doe@example.com");
        when(mockUser.getUsername()).thenReturn("johndoe");
        when(mockUser.getPassword()).thenReturn("password123");
        when(mockUser.getPhone()).thenReturn("123-456-7890");
        when(mockUser.getAddress()).thenReturn("123 Main St");
        when(mockUser.getSex()).thenReturn("Male");
        when(mockUser.getDob()).thenReturn("01/01/1990");
        when(mockUser.isAccountNonExpired()).thenReturn(true);
        when(mockUser.isAccountNonLocked()).thenReturn(true);
        when(mockUser.isCredentialsNonExpired()).thenReturn(true);
        when(mockUser.isEnabled()).thenReturn(true);

        // Call the displayInformation method
        serviceInterface.displayInformation(mockUser);

        // Verifying that the methods on the User object were called
        verify(mockUser).getUserId();
        verify(mockUser).getFirstname();
        verify(mockUser).getLastname();
        verify(mockUser).getEmail();
        verify(mockUser).getUsername();
        verify(mockUser).getPassword();
        verify(mockUser).getPhone();
        verify(mockUser).getAddress();
        verify(mockUser).getSex();
        verify(mockUser).getDob();
        verify(mockUser).isAccountNonExpired();
        verify(mockUser).isAccountNonLocked();
        verify(mockUser).isCredentialsNonExpired();
        verify(mockUser).isEnabled();
    }
}
