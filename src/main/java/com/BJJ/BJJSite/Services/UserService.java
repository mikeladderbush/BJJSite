package com.BJJ.BJJSite.Services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Exceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Exceptions.UserNotFoundException;
import com.BJJ.BJJSite.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user using the provided UserDto and assigns a default role.
     *
     * @param userDto The UserDto containing user information.
     * @return The created User entity.
     */
    public User createUser(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("User data must not be null");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        // Convert UserDto to User entity
        User user = convertDtoToEntity(userDto);
        user.setPassword(encodedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        // Assign default role
        Set<String> roles = new HashSet<>();
        roles.add("USER"); // Default role
        user.setRoles(roles);

        return userRepository.save(user);
    }

    /**
     * Updates an existing user by their email using the provided UserDto.
     *
     * @param email   The email of the user to update.
     * @param userDto The UserDto containing updated user information.
     * @return An Optional containing the updated User entity.
     */
    @Transactional
    public User updateUser(String email, UserDto userDto) {
        if (email == null || userDto == null) {
            throw new IllegalArgumentException("Email and updated user data must not be null");
        }

        User oldUser = userRepository.getUserByEmail(email);
        updateEntityFromDto(oldUser, userDto);
        return userRepository.getUserByEmail(email);
    }

    /**
     * Deletes a user by their email.
     *
     * @param email The email of the user to delete.
     * @return True if the user was deleted, otherwise false.
     */
    @Transactional
    public void deleteUser(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }

        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        userRepository.deleteUserByEmail(email);
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto The UserDto to convert.
     * @return The User entity.
     */
    private User convertDtoToEntity(UserDto userDto) {
        return User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .dob(userDto.getDob())
                .sex(userDto.getSex())
                // Password is set separately after encoding
                .build();
    }

    /**
     * Updates a User entity with data from a UserDto.
     *
     * @param user    The existing User entity to update.
     * @param userDto The UserDto containing updated information.
     */
    private void updateEntityFromDto(User user, UserDto userDto) {
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setDob(userDto.getDob());
        user.setSex(userDto.getSex());

        // Update password if provided
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);
        }

        // Update roles if provided (optional)
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<String> updatedRoles = userDto.getRoles().stream()
                    .map(role -> role) // Ensure "ROLE_" prefix is added
                    .collect(Collectors.toSet());
            user.setRoles(updatedRoles);
        }
    }
}
