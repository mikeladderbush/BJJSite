package com.BJJ.BJJSite.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.UserFactory;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Services.UserService;

/**
 * REST controller for managing Users.
 * 
 * Provides endpoints to create, retrieve, update, and delete users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    /**
     * Constructor for UserController.
     * 
     * @param userFactory The factory used to create User instances.
     * @param userRepository The repository used to interact with User data.
     */
    @Autowired
    public UserController(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    @Autowired
    private UserService userService;

    /**
     * Retrieves a User by its ID.
     * 
     * @param id The ID of the User.
     * @return A ResponseEntity containing the User if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUser(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new User.
     * 
     * @return An Optional containing the created User.
     */
    @PostMapping("/{id}")
    public Optional<User> createUser() {
        return userFactory.createUser();
    }

    /**
     * Updates an existing User.
     * 
     * @param id The ID of the User to be updated.
     * @param update The updated User data.
     * @return A ResponseEntity containing the updated User if found, or a 404 status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User update) {
        Optional<User> userOptional = userRepository.findById(id);
        userService.updateUser(userOptional.get().getEmail(), update);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a User by its ID.
     * 
     * @param id The ID of the User to be deleted.
     * @return A ResponseEntity with a 200 status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
