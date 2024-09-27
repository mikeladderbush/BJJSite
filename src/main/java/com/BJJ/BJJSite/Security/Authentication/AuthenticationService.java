package com.BJJ.BJJSite.Security.Authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Security.JWT.JwtService;
import com.BJJ.BJJSite.Services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    // Using DTO for registration
    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var userDto = requestToUserDto(registerRequest);

        var user = userService.createUser(userDto);

        // Generate JWT token for the newly registered user
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    // Authentication remains the same
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        // Retrieve user and generate JWT
        var user = repository.getUserByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public UserDto requestToUserDto(RegisterRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getEmail());
        userDto.setPassword(request.getPassword());
        userDto.setFirstname(request.getFirstname());
        userDto.setLastname(request.getLastname());
        return userDto;
    }
}
