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
import com.BJJ.BJJSite.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFactory userFactory;

    @Autowired
    public UserController(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUser(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public User createUser() {
        return userFactory.createUser();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User update) {
        Optional<User> userOptional = userService.updateUser(id, update);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
