package com.BJJ.BJJSite.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for sending user data in API responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Integer userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String dob;
    private String sex;

}
