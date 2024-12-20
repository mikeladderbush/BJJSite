package com.BJJ.BJJSite.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import com.BJJ.BJJSite.Enums.Memberships;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstname;

    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastname;

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phone;

    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    private Memberships membership;
    
    private Set<String> roles;
}
