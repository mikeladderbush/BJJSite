package com.BJJ.BJJSite.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a User by its ID.
     * 
     * @param id The ID of the User.
     * @return A ResponseEntity containing the User if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            EntityModel<User> resource = EntityModel.of(user);
            resource.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
            resource.add(linkTo(methodOn(UserController.class).createUser()).withRel("create-user"));
            resource.add(linkTo(methodOn(UserController.class).updateUser(id, user)).withRel("update-user"));
            resource.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete-user"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new User.
     * 
     * @return A ResponseEntity containing the created User.
     */
    @PostMapping
    public ResponseEntity<EntityModel<User>> createUser() {
        User createdUser = userFactory.createUser().orElseThrow(() -> new RuntimeException("User creation failed"));
        EntityModel<User> resource = EntityModel.of(createdUser);
        resource.add(linkTo(methodOn(UserController.class).getUserById(createdUser.getUserId())).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    /**
     * Updates an existing User.
     * 
     * @param id The ID of the User to be updated.
     * @param update The updated User data.
     * @return A ResponseEntity containing the updated User if found, or a 404 status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Integer id, @RequestBody User update) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.updateUser(user.getEmail(), update);
            EntityModel<User> resource = EntityModel.of(user);
            resource.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
            resource.add(linkTo(methodOn(UserController.class).createUser()).withRel("create-user"));
            resource.add(linkTo(methodOn(UserController.class).updateUser(id, user)).withRel("update-user"));
            resource.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete-user"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a User by its ID.
     * 
     * @param id The ID of the User to be deleted.
     * @return A ResponseEntity with a 200 status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
