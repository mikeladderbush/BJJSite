package com.BJJ.BJJSite.Security.JWT;

import com.BJJ.BJJSite.Classes.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;

    @Mock
    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
    }

    @Test
    void testExtractUsername() {
        // Generate a token
        String email = "test@example.com";
        String token = jwtService.generateToken(mockUser);

        when(mockUser.getUsername()).thenReturn(email);
        when(mockUser.getEmail()).thenReturn(email);

        // Extract the username from the token
        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(email, extractedUsername);
    }

    @Test
    void testGenerateToken() {
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Generate a token for the mock user
        String token = jwtService.generateToken(mockUser);

        assertNotNull(token);
        assertFalse(token.isEmpty());

        // Parse the token and verify claims
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtService.getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("test@example.com", claims.getSubject());
    }

    @Test
    void testIsTokenValid_True() {
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Generate a valid token for the mock user
        String token = jwtService.generateToken(mockUser);

        // Mock the username
        when(mockUser.getUsername()).thenReturn("test@example.com");

        // Check if the token is valid
        boolean isValid = jwtService.isTokenValid(token, mockUser);
        assertTrue(isValid);
    }

    @Test
    void testIsTokenValid_False() {
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Generate a token
        String token = jwtService.generateToken(mockUser);

        // Mock the username with an invalid email
        when(mockUser.getUsername()).thenReturn("invalid@example.com");

        // Check if the token is invalid due to the wrong username
        boolean isValid = jwtService.isTokenValid(token, mockUser);
        assertFalse(isValid);
    }

    @Test
    void testIsTokenExpired_False() {
        // Generate a token that expires in 24 hours
        String token = jwtService.generateToken(mockUser);

        // Check if the token is not expired
        assertFalse(jwtService.isTokenExpired(token));
    }

    @Test
    void testIsTokenExpired_True() {
        // Generate a token with a short expiration
        when(mockUser.getEmail()).thenReturn("test@example.com");
        Map<String, Object> extraClaims = new HashMap<>();
        String token = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject("test@example.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // Token expired 1 second ago
                .signWith(jwtService.getSignInKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();

        // Check if the token is expired
        assertTrue(jwtService.isTokenExpired(token));
    }

    @Test
    void testExtractClaim() {
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Generate a token
        String token = jwtService.generateToken(mockUser);

        // Extract the expiration claim from the token
        Date expiration = jwtService.extractClaim(token, Claims::getExpiration);

        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }
}
