package com.BJJ.BJJSite.Repositories;

import com.BJJ.BJJSite.Classes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .password("password123")
                .buildUser();

        userRepository.save(user);
    }

    @Test
    void testFindByUsername_Success() {
        Optional<User> foundUser = userRepository.findByUsername("johndoe");

        assertTrue(foundUser.isPresent());
        assertEquals("johndoe", foundUser.get().getUsername());
    }

    @Test
    void testFindByUsername_NotFound() {
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByEmail_Success() {
        Optional<User> foundUser = userRepository.findByEmail("john.doe@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("john.doe@example.com", foundUser.get().getEmail());
    }

    @Test
    void testFindByEmail_NotFound() {
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindById_Success() {
        Optional<User> foundUser = userRepository.findById(user.getUserId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getUserId(), foundUser.get().getUserId());
    }

    @Test
    void testFindById_NotFound() {
        Optional<User> foundUser = userRepository.findById(999);

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testExistsById_Success() {
        boolean exists = userRepository.existsById(user.getUserId());

        assertTrue(exists);
    }

    @Test
    void testExistsById_NotFound() {
        boolean exists = userRepository.existsById(999);

        assertFalse(exists);
    }

    @Test
    void testDeleteById_Success() {
        userRepository.deleteById(user.getUserId());

        Optional<User> deletedUser = userRepository.findById(user.getUserId());

        assertFalse(deletedUser.isPresent());
    }

    @Test
    void testExistsByEmail_Success() {
        boolean exists = userRepository.existsByEmail("john.doe@example.com");

        assertTrue(exists);
    }

    @Test
    void testExistsByEmail_NotFound() {
        boolean exists = userRepository.existsByEmail("nonexistent@example.com");

        assertFalse(exists);
    }
}
