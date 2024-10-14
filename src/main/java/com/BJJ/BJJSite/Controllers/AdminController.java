package com.BJJ.BJJSite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Dto.RoleAssignmentDto;
import com.BJJ.BJJSite.Repositories.UserRepository;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@EnableMethodSecurity(prePostEnabled = true)
public class AdminController extends UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> assignRole(@Valid @RequestBody RoleAssignmentDto roleAssignmentDto,
            @PathVariable String email) {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found when updating roles.");
        }
        userService.updateRolesFromDto(user, roleAssignmentDto);
        return ResponseEntity.ok(user.getEmail());
    }

}
