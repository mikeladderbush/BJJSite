package com.BJJ.BJJSite.Security.Authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Security.JWT.JwtService;
import com.BJJ.BJJSite.Services.UserService;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(UserDto request) {

        request.setRoles(Set.of("USER"));

        User createdUser = userService.createUser(request);

        var jwtToken = jwtService.generateToken(createdUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(UserDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = repository.getUserByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
