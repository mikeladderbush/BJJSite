package com.BJJ.BJJSite.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Repositories.UserRepository;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_NewUser() throws Exception {
        User user = new User.UserBuilder<>()
                .email("newuser@example.com")
                .username("newuser")
                .password("password")
                .buildUser();

        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
        verify(userRepository, times(1)).findByEmail("newuser@example.com");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser_UserExists() {
        User existingUser = new User.UserBuilder<>()
                .email("user@example.com")
                .username("user")
                .password("oldpassword")
                .buildUser();

        User updatedUser = new User.UserBuilder<>()
                .email("user@example.com")
                .username("user")
                .password("newpassword")
                .buildUser();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        Optional<User> result = userService.updateUser("user@example.com", updatedUser);

        assertTrue(result.isPresent());
        assertEquals("newpassword", result.get().getPassword());
        verify(userRepository, times(1)).findByEmail("user@example.com");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUser_UserDoesNotExist() {
        User updatedUser = new User.UserBuilder<>()
                .email("user@example.com")
                .username("user")
                .password("newpassword")
                .buildUser();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.updateUser("user@example.com", updatedUser);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByEmail("user@example.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUser() {
        User user = new User.UserBuilder<>()
                .email("user@example.com")
                .username("user")
                .buildUser();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUser(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByUsername() {
        User user = new User.UserBuilder<>()
                .email("user@example.com")
                .username("user")
                .buildUser();

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername("user");

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
