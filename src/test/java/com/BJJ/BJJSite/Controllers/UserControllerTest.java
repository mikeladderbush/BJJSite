package com.BJJ.BJJSite.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.util.Set;
import java.util.HashSet;

import jakarta.transaction.Transactional;

import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Security.Authentication.AuthenticationRequest;
import com.BJJ.BJJSite.Security.Authentication.RegisterRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @Test
    @Transactional
    void setup() throws Exception {

        if (userRepository.existsByEmail("user@test.com")) {
            userRepository.deleteUserByEmail("user@test.com");
        }
        RegisterRequest regRequest = new RegisterRequest();
        regRequest.setEmail("user@test.com");
        regRequest.setFirstname("test");
        regRequest.setLastname("user");
        regRequest.setPassword("mike");
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(regRequest)))
                .andExpect(status().isOk());

        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setEmail("user@test.com");
        authRequest.setPassword("mike");

        MvcResult result = mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        jwtToken = JsonPath.parse(response).read("$.token");

    }

}
