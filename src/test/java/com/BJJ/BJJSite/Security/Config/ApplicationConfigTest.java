package com.BJJ.BJJSite.Security.Config;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ApplicationConfigTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserDetailsService_UserFound() {
        // Mock a user of type com.BJJ.BJJSite.Classes.User
        User mockUser = new User();
        mockUser.setEmail("john.doe@example.com");
        mockUser.setPassword("password123");
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
    
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(mockUser));
    
        UserDetailsService userDetailsService = applicationConfig.userDetailsService();
        assertNotNull(userDetailsService);
    
        UserDetails user = userDetailsService.loadUserByUsername("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getUsername());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }
    

    @Test
    void testUserDetailsService_UserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        UserDetailsService userDetailsService = applicationConfig.userDetailsService();
        assertNotNull(userDetailsService);

        assertThrows(RuntimeException.class, () -> userDetailsService.loadUserByUsername("nonexistent@example.com"));
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testAuthenticationProvider_Success() {
        AuthenticationProvider authProvider = applicationConfig.authenticationProvider();
        assertNotNull(authProvider);
        assertTrue(authProvider instanceof DaoAuthenticationProvider);
    
        // Mock your custom User entity
        User mockUser = new User();
        mockUser.setEmail("john.doe@example.com");
        mockUser.setPassword("encoded_password"); // This should be the encoded password
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
    
        // Simulate the user repository returning the custom User object
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(mockUser));
    
        // Simulate the password encoder matching the password
        when(passwordEncoder.matches("password123", "encoded_password")).thenReturn(true);
    
        // Test authentication
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken("john.doe@example.com", "password123");
        assertDoesNotThrow(() -> authProvider.authenticate(authToken));
    }
    

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof PasswordEncoder);

        // Test password encoding and matching
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}
