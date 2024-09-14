package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.UserRepository;

import java.util.Optional;

/**
 * Service class for managing User entities.
 * 
 * This class provides methods to create, update, retrieve, and delete User
 * entities, handling business logic and interactions with the UserRepository.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     * 
     * @param userRepository The repository used to interact with User data.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new User.
     * 
     * If a User with the same email already exists, it updates the existing one
     * instead.
     * 
     * @param <T>  The type of User.
     * @param user The User to be created.
     * @return The created or updated User.
     * @throws UserAlreadyExistsException If a User with the same email already
     *                                    exists.
     */
    @Transactional
    public <T extends User> T createUser(T user) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            System.out.println("User with email " + user.getEmail() + " already exists. Updating prior user instead");
            updateUser(existingUser.get().getEmail(), user);
        }

        return userRepository.save(user);
    }

    /**
     * Updates an existing User based on their email.
     * 
     * This method updates the fields of an existing User with the values from the
     * provided update object.
     * 
     * @param userEmail The email of the User to be updated.
     * @param update    The updated User data.
     * @return An Optional containing the updated User if found, or an empty
     *         Optional if not found.
     */
    public Optional<User> updateUser(String userEmail, User update) {
        return userRepository.findByEmail(userEmail).map(user -> {
            if (update.getFirstname() != null) {
                user.setFirstname(update.getFirstname());
            }
            if (update.getLastname() != null) {
                user.setLastname(update.getLastname());
            }
            if (update.getUsername() != null) {
                user.setUsername(update.getUsername());
            }
            if (update.getPassword() != null) {
                user.setPassword(update.getPassword());
            }
            if (update.getEmail() != null) {
                user.setEmail(update.getEmail());
            }
            if (update.getPhone() != null) {
                user.setPhone(update.getPhone());
            }
            if (update.getAddress() != null) {
                user.setAddress(update.getAddress());
            }
            if (update.getSex() != null) {
                user.setSex(update.getSex());
            }
            if (update.getDob() != null) {
                user.setDob(update.getDob());
            }
            if (update.getPaymentOptions() != null) {
                user.getPaymentOptions().clear();
                user.getPaymentOptions().addAll(update.getPaymentOptions());
            }
            return userRepository.save(user);
        });
    }

    /**
     * Retrieves a User by their ID.
     * 
     * @param integer The ID of the User.
     * @return An Optional containing the User if found, or an empty Optional if not
     *         found.
     */
    public Optional<User> getUser(Integer integer) {
        return userRepository.findById(integer);
    }

    /**
     * Finds a User by their username.
     * 
     * @param username The username of the User.
     * @return An Optional containing the User if found, or an empty Optional if not
     *         found.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Deletes a User by their ID.
     * 
     * @param integer The ID of the User to be deleted.
     */
    public void deleteUser(Integer integer) {
        userRepository.deleteById(integer);
    }
}
