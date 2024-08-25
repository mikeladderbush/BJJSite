package com.BJJ.BJJSite.Controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Services.UserService;

/*TODO getting the following error for test template:  
 * UserControllerTest.testCreateUser ┬╗ IllegalState ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to 
 * load context for [WebMergedContextConfiguration@8ce4320 testClass = com.BJJ.BJJSite.Controllers.UserControllerTest

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUserById() throws Exception {
        User user = new User.UserBuilder<>()
                .username("testuser")
                .email("testuser@example.com")
                .buildUser();

        when(userService.getUser(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        when(userService.getUser(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User.UserBuilder<>()
                .username("newuser")
                .email("newuser@example.com")
                .buildUser();

        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"newuser\", \"email\": \"newuser@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        User existingUser = new User.UserBuilder<>()
                .username("existinguser")
                .email("existinguser@example.com")
                .buildUser();

        when(userService.updateUser("existinguser@example.com", existingUser)).thenReturn(Optional.of(existingUser));

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"updateduser\", \"email\": \"existinguser@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updateduser"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }
}

*/
