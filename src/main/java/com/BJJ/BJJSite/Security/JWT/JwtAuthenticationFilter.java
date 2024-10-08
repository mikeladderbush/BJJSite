package com.BJJ.BJJSite.Security.JWT;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api/v1/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if Authorization header is valid
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header missing or invalid");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Extract JWT from header
        userEmail = jwtService.extractUsername(jwt); // Extract email/username from JWT
        System.out.println("Extracted email from token: " + userEmail);

        // Ensure email is not null and authentication is not already set
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("Loaded UserDetails: " + userDetails);

            // Validate the token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                Claims claims = jwtService.extractClaims(jwt);

                // Extract roles from JWT and convert to GrantedAuthority
                List<String> roles = claims.get("roles", List.class);
                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                System.out.println("Roles extracted from token: " + roles);
                System.out.println("Authorities set for the user: " + authorities);

                // Use the extracted authorities (not userDetails.getAuthorities())
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, authorities); // Use `authorities` here

                // Set additional details
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // Log the current authentication state
                System.out.println("Authentication set in SecurityContextHolder: " +
                        SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            } else {
                System.out.println("Token validation failed");
            }
        }
        filterChain.doFilter(request, response);
    }
}
