package com.BJJ.BJJSite.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security configuration class for the application.
 * 
 * This class configures the security settings, including HTTP security, session
 * management, CSRF protection, and password encoding.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationConfiguration implements WebMvcConfigurer {

    /**
     * Configures the security filter chain.
     * 
     * This method sets up the security filter chain, which includes allowing all
     * HTTP requests,
     * disabling CSRF protection, setting session management to stateless, and
     * configuring headers.
     * 
     * @param http The HttpSecurity object used to configure the security settings.
     * @return The configured SecurityFilterChain object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/about", "/login", "/contact").permitAll() // Public access
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Configures the password encoder bean.
     * 
     * This method provides a BCryptPasswordEncoder bean, which is used for encoding
     * passwords in the application.
     * 
     * @return A PasswordEncoder instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncorder() {
        return new BCryptPasswordEncoder();
    }
}
