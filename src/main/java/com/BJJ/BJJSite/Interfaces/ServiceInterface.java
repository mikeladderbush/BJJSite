package com.BJJ.BJJSite.Interfaces;

import java.security.SecureRandom;

import com.BJJ.BJJSite.Classes.User;

public interface ServiceInterface {

    public static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a random ID for objects.
     * 
     * @return A randomly generated ID for.
     */
    public default Long generateId() {
        return Math.abs(secureRandom.nextLong());
    }

    public default void displayInformation(User user) {
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Sex: " + user.getSex());
        System.out.println("DOB: " + user.getDob());
        System.out.println("Account non-expired: " + user.getIsAccountNonExpired());
        System.out.println("Account non-locked: " + user.getIsAccountNonLocked());
        System.out.println("Credentials non-expired: " + user.getIsCredentialsNonExpired());
        System.out.println("Enabled: " + user.getIsEnabled());
    }

}
