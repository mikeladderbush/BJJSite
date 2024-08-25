package com.BJJ.BJJSite.Repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.BJJ.BJJSite.Classes.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder<>()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .buildUser();
        userRepository.save(user);
    }

    @Test
    void testFindByUsername() {
        // Act
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        // Assert
        assertTrue(foundUser.isPresent(), "User should be found by username");
        assertEquals("testuser", foundUser.get().getUsername(), "Username should match");
    }

    @Test
    void testFindByEmail() {
        // Act
        Optional<User> foundUser = userRepository.findByEmail("testuser@example.com");

        // Assert
        assertTrue(foundUser.isPresent(), "User should be found by email");
        assertEquals("testuser@example.com", foundUser.get().getEmail(), "Email should match");
    }

    @Test
    void testFindByUsernameNotFound() {
        // Act
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        // Assert
        assertFalse(foundUser.isPresent(), "User should not be found by nonexistent username");
    }

    @Test
    void testFindByEmailNotFound() {
        // Act
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Assert
        assertFalse(foundUser.isPresent(), "User should not be found by nonexistent email");
    }
}
