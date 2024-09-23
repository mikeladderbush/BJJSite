package com.BJJ.BJJSite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Dto.UserResponseDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST controller for managing Users.
 * 
 * Provides endpoints to create, retrieve, update, and delete users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Retrieves a User by its ID.
     * 
     * @param id The ID of the User.
     * @return A ResponseEntity containing the User if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<EntityModel<UserResponseDto>> getUserById(@PathVariable Integer id) {
        User user = userRepository.getUserById(id);
        UserResponseDto userResponseDto = convertEntityToResponseDto(user);

        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);
        resource.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create-user"));
        resource.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("update-user"));
        resource.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete-user"));
        return ResponseEntity.ok(resource);
    }

    /**
     * Creates a new User.
     * 
     * @param userDto The UserDto containing user information.
     * @return A ResponseEntity containing the created User.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<EntityModel<UserResponseDto>> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        UserResponseDto userResponseDto = convertEntityToResponseDto(createdUser);

        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);
        resource.add(linkTo(methodOn(UserController.class).getUserById(createdUser.getId())).withSelfRel());
        return ResponseEntity.status(201).body(resource);
    }

    /**
     * Updates an existing User.
     * 
     * @param id       The ID of the User to be updated.
     * @param userDto The updated User data.
     * @return A ResponseEntity containing the updated User if found, or a 404 status if not found.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<EntityModel<UserResponseDto>> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUserById(id, userDto);
        UserResponseDto userResponseDto = convertEntityToResponseDto(updatedUser);

        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);
        resource.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create-user"));
        resource.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete-user"));
        return ResponseEntity.ok(resource);
    }

    /**
     * Deletes a User by its ID.
     * 
     * @param id The ID of the User to be deleted.
     * @return A ResponseEntity with a 200 status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Converts a User entity to a UserResponseDto.
     *
     * @param user The User entity to convert.
     * @return The UserResponseDto.
     */
    private UserResponseDto convertEntityToResponseDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .dob(user.getDob())
                .sex(user.getSex())
                // Exclude password and sensitive fields
                .build();
    }
}
