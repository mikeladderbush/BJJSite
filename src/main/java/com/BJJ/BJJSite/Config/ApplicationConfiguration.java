package com.BJJ.BJJSite.Config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /*
     * At this moment this won't work because I need to set up the authorites and my
     * user classes to implement userdetails
     * 
     * @Bean
     * public UserDetailsService userDetailsService() {
     * 
     * JdbcUserDetailsManager userDetailsManager = new
     * JdbcUserDetailsManager(dataSource);
     * List authoritiesContainer = new ArrayList<GrantedAuthority>();
     * // Create users here until API is set up
     * UserDetails placeholderUser = new User("User1", "Pass1", true, true, true,
     * true,
     * authoritiesContainer);
     * userDetailsManager.createUser(placeholderUser);
     * return userDetailsManager;
     * }
     */

    @Bean
    public PasswordEncoder passwordEncorder() {

        return new BCryptPasswordEncoder();

    }
}
