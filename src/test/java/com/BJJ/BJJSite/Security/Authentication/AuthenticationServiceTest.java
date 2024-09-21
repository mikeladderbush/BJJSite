package com.BJJ.BJJSite.Security.Authentication;

import com.BJJ.BJJSite.Classes.Role;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Security.JWT.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@example.com", "password123");

        User mockUser = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("encoded_password")
                .role(Role.ADMIN)
                .buildUser();

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(jwtService.generateToken(mockUser)).thenReturn("mock-jwt-token");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertEquals("mock-jwt-token", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(mockUser);
    }

    @Test
    void testAuthenticate_Success() {
        AuthenticationRequest authRequest = new AuthenticationRequest("john.doe@example.com", "password123");

        User mockUser = new User.UserBuilder<>()
                .email("john.doe@example.com")
                .password("encoded_password")
                .role(Role.ADMIN)
                .buildUser();

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(mockUser)).thenReturn("mock-jwt-token");

        doNothing().when(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        AuthenticationResponse response = authenticationService.authenticate(authRequest);

        assertEquals("mock-jwt-token", response.getToken());
        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(jwtService, times(1)).generateToken(mockUser);
    }
}
