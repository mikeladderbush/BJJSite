package com.BJJ.BJJSite.Controllers;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Services.UserService;
import com.BJJ.BJJSite.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetUserById_ReturnsUser() throws Exception {
        User mockUser = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .buildUser();

        when(userService.getUser(anyInt())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.create-user.href").exists())
                .andExpect(jsonPath("$._links.update-user.href").exists())
                .andExpect(jsonPath("$._links.delete-user.href").exists());
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUser(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser_ReturnsCreatedUser() throws Exception {
        User mockUser = new User.UserBuilder<>()
                .firstname("Jane")
                .lastname("Smith")
                .username("janesmith")
                .password("password123")
                .email("jane.smith@example.com")
                .buildUser();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);
        when(userService.createUser(null)).thenReturn(Optional.of(mockUser));

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.email").value("jane.smith@example.com"))
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    void testUpdateUser_ReturnsUpdatedUser() throws Exception {
        User mockUser = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .buildUser();

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));
        when(userService.updateUser(Mockito.anyString(), Mockito.any(User.class))).thenReturn(mockUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"updated.email@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    void testUpdateUser_NotFound() throws Exception {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"updated.email@example.com\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser_ReturnsOk() throws Exception {
        when(userRepository.existsById(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser_NotFound() throws Exception {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNotFound());
    }
}
