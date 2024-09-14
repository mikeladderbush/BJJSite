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

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUser(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public Optional<User> createUser() {
        return userFactory.createUser();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Integer id, @RequestBody User update) {
        Optional<User> userOptional = userRepository.findById(id);
        userService.updateUser(userOptional.get().getEmail(), update);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

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
