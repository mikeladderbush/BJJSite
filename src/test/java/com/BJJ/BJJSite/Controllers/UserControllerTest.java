package com.BJJ.BJJSite.Controllers;

import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @BeforeEach
    void setup() throws Exception {

        // Clean up any existing users with this email
        if (userRepository.existsByEmail("user@test.com")) {
            userRepository.deleteUserByEmail("user@test.com");
        }

        UserDto regRequest = new UserDto();
        regRequest.setEmail("user@test.com");
        regRequest.setFirstname("user");
        regRequest.setLastname("test");
        regRequest.setPassword("password123");
        regRequest.setUsername("usertest");

        // Register a new user for authentication
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(regRequest)))
                .andExpect(status().isOk());

        // Authenticate the user to retrieve JWT
        MvcResult result = mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(regRequest)))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JWT from response
        String response = result.getResponse().getContentAsString();
        jwtToken = JsonPath.parse(response).read("$.token");

    }

    @Test
    public void testGetUser() throws Exception {
        // Test fetching an existing user
        mockMvc.perform(get("/api/users/user@test.com")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user@test.com"));
    }

    @Test
    public void testCreateUser() throws Exception {
        // Use a different email for this test to avoid conflict
        UserDto userDto = new UserDto();
        userDto.setEmail("newuser@test.com");
        userDto.setFirstname("new");
        userDto.setLastname("user");
        userDto.setPassword("newpassword123");
        userDto.setUsername("newusertest");
        userDto.setRoles(Set.of("ROLE_USER"));

        // Create a new user
        mockMvc.perform(post("/api/users")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("newuser@test.com"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Create the user before updating it
        UserDto userDto = new UserDto();
        userDto.setEmail("updateuser@test.com");
        userDto.setFirstname("update");
        userDto.setLastname("user");
        userDto.setPassword("updatepassword123");
        userDto.setUsername("updateusertest");
        userDto.setRoles(Set.of("ROLE_USER"));

        mockMvc.perform(post("/api/users")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        // Update user details with valid data
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setFirstname("updated");
        updatedUserDto.setLastname("user");
        updatedUserDto.setUsername("updatedusertest");
        updatedUserDto.setEmail("updateuser@test.com"); // Make sure email is provided

        mockMvc.perform(put("/api/users/updateuser@test.com")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("updated"))
                .andExpect(jsonPath("$.lastname").value("user"))
                .andExpect(jsonPath("$.username").value("updatedusertest"));
    }

}
