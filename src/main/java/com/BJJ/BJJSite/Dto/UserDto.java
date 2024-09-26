package com.BJJ.BJJSite.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstname;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastname;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phone;

    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    @Size(max = 10, message = "Sex must be at most 10 characters")
    private String sex;

    @Size(max = 10, message = "Date of birth must be at most 10 characters")
    private String dob;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    // New field for roles
    private Set<String> roles;
}
