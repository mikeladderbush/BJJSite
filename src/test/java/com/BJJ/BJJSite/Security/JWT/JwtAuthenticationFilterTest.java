package com.BJJ.BJJSite.Security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext(); // Clear the security context before each test
    }

    @Test
    void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        // Test scenario where no Authorization header is present
        when(httpServletRequest.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);
        verify(jwtService, never()).extractUsername(anyString());
    }

    @Test
    void testDoFilterInternal_InvalidAuthorizationHeader() throws ServletException, IOException {
        // Test scenario where the Authorization header doesn't start with "Bearer "
        when(httpServletRequest.getHeader("Authorization")).thenReturn("InvalidToken");

        jwtAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);
        verify(jwtService, never()).extractUsername(anyString());
    }

    @Test
    void testDoFilterInternal_ValidTokenAndUser() throws ServletException, IOException {
        // Test scenario where the JWT token is valid and the user is authenticated
        String validToken = "Bearer validToken";
        String userEmail = "john.doe@example.com";
        UserDetails mockUserDetails = mock(UserDetails.class);

        when(httpServletRequest.getHeader("Authorization")).thenReturn(validToken);
        when(jwtService.extractUsername("validToken")).thenReturn(userEmail);
        when(userDetailsService.loadUserByUsername(userEmail)).thenReturn(mockUserDetails);
        when(jwtService.isTokenValid("validToken", mockUserDetails)).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        verify(userDetailsService, times(1)).loadUserByUsername(userEmail);
        verify(jwtService, times(1)).isTokenValid("validToken", mockUserDetails);
        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);

        // Ensure that the SecurityContext contains the authenticated user
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Test scenario where the JWT token is invalid
        String invalidToken = "Bearer invalidToken";
        String userEmail = "john.doe@example.com";
        UserDetails mockUserDetails = mock(UserDetails.class);

        when(httpServletRequest.getHeader("Authorization")).thenReturn(invalidToken);
        when(jwtService.extractUsername("invalidToken")).thenReturn(userEmail);
        when(userDetailsService.loadUserByUsername(userEmail)).thenReturn(mockUserDetails);
        when(jwtService.isTokenValid("invalidToken", mockUserDetails)).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        // Ensure no authentication is set in the SecurityContext
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);
    }
}
