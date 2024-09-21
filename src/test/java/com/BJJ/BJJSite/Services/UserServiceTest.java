package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a mock user
        mockUser = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .password("password123")
                .phone("123-456-7890")
                .address("123 Main St")
                .sex("Male")
                .dob("01-01-1990")
                .paymentOptions(new ArrayList<>())
                .buildUser();
    }

    @Test
    void testCreateUser_Success() throws UserAlreadyExistsException {
        // Mock that no user exists with the same email
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        // Call createUser method
        User createdUser = userService.createUser(mockUser);

        assertNotNull(createdUser);
        assertEquals(mockUser.getEmail(), createdUser.getEmail());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testCreateUser_AlreadyExists() throws UserAlreadyExistsException {
        // Mock that a user with the same email already exists
        User existingUser = new User.UserBuilder<>()
                .firstname("Jane")
                .lastname("Doe")
                .email("john.doe@example.com")
                .buildUser();

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Call createUser method, expecting an update
        User updatedUser = userService.createUser(mockUser);

        assertNotNull(updatedUser);
        assertEquals(existingUser.getEmail(), updatedUser.getEmail());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUser_Success() {
        // Mock finding the user by email
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(mockUser));

        // Create an updated user
        User updatedUser = new User.UserBuilder<>()
                .firstname("Updated John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .buildUser();

        when(userRepository.save(mockUser)).thenReturn(mockUser);

        // Call updateUser method
        User result = userService.updateUser("john.doe@example.com", updatedUser);

        assertNotNull(result);
        assertEquals("Updated John", result.getFirstname());
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testUpdateUser_NotFound() {
        // Mock that the user does not exist
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Call updateUser method, expecting an exception
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser("nonexistent@example.com", mockUser));
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testGetUser_Success() {
        // Mock finding the user by ID
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        // Call getUser method
        Optional<User> foundUser = userService.getUser(1);

        assertTrue(foundUser.isPresent());
        assertEquals(mockUser.getEmail(), foundUser.get().getEmail());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUser_NotFound() {
        // Mock user not found by ID
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Call getUser method
        Optional<User> foundUser = userService.getUser(1);

        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindByUsername_Success() {
        // Mock finding the user by username
        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(mockUser));

        // Call findByUsername method
        Optional<User> foundUser = userService.findByUsername("johndoe");

        assertTrue(foundUser.isPresent());
        assertEquals("johndoe", foundUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("johndoe");
    }

    @Test
    void testDeleteUser_Success() {
        // Mock repository deleteById
        doNothing().when(userRepository).deleteById(1);

        // Call deleteUser method
        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }
}
