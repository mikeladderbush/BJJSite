package com.BJJ.BJJSite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Dto.UserDto;
import com.BJJ.BJJSite.Dto.UserResponseDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.hibernate.mapping.Set;

@RestController
@RequestMapping("/api/users")
@EnableMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<UserResponseDto>> getUser(@PathVariable String email) {
        User user = userRepository.getUserByEmail(email);

        UserResponseDto userResponseDto = convertEntityToResponseDto(user);
        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);

        // Adding HATEOAS links
        resource.add(linkTo(methodOn(UserController.class).getUser(email)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create-user"));
        resource.add(linkTo(methodOn(UserController.class).updateUser(email, null)).withRel("update-user"));
        resource.add(linkTo(methodOn(UserController.class).deleteUser(email)).withRel("delete-user"));

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<UserResponseDto>> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        UserResponseDto userResponseDto = convertEntityToResponseDto(createdUser);

        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);
        resource.add(linkTo(methodOn(UserController.class).getUser(createdUser.getEmail())).withSelfRel());
        resource.add(
                linkTo(methodOn(UserController.class).updateUser(createdUser.getEmail(), null)).withRel("update-user"));
        resource.add(linkTo(methodOn(UserController.class).deleteUser(createdUser.getEmail())).withRel("delete-user"));

        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<UserResponseDto>> updateUser(
            @PathVariable String email,
            @Valid @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(email, userDto);

        UserResponseDto userResponseDto = convertEntityToResponseDto(updatedUser);
        EntityModel<UserResponseDto> resource = EntityModel.of(userResponseDto);

        resource.add(linkTo(methodOn(UserController.class).getUser(email)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create-user"));
        resource.add(linkTo(methodOn(UserController.class).deleteUser(email)).withRel("delete-user"));

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userRepository.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
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
                .build();
    }
}
