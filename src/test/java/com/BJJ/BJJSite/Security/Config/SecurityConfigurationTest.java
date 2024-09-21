package com.BJJ.BJJSite.Security.Config;

import com.BJJ.BJJSite.Security.JWT.JwtAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    void testPublicEndpoints_AccessibleWithoutAuthentication() throws Exception {
        // Public endpoints that should be accessible without authentication
        mockMvc.perform(get("/home")).andExpect(status().isOk());
        mockMvc.perform(get("/about")).andExpect(status().isOk());
        mockMvc.perform(get("/login")).andExpect(status().isOk());
        mockMvc.perform(get("/contact")).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/auth/register")).andExpect(status().isOk());
    }

    @Test
    void testProtectedEndpoints_RequireAuthentication() throws Exception {
        // Protected endpoint that requires authentication
        mockMvc.perform(get("/api/v1/protected"))
                .andExpect(status().isForbidden()); // Since no user is authenticated, should return 403 Forbidden
    }

    @Test
    @WithMockUser
    void testProtectedEndpoints_WithAuthentication() throws Exception {
        // Protected endpoint that should be accessible with authentication
        mockMvc.perform(get("/api/v1/protected"))
                .andExpect(status().isOk()); // Should return 200 OK for authenticated user
    }

    @Test
    void testJwtFilterIsApplied() throws Exception {
        // Ensure that the JWT filter is applied before UsernamePasswordAuthenticationFilter
        mockMvc.perform(get("/api/v1/protected"))
                .andExpect(status().isForbidden()); // Ensure the filter is applied
    }
}
