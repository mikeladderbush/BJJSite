package com.BJJ.BJJSite.Interfaces;

import java.security.SecureRandom;

import com.BJJ.BJJSite.Classes.User;

/**
 * Interface defining common service operations.
 * 
 * The ServiceInterface provides default methods for generating IDs and displaying user information.
 */
public interface ServiceInterface {

    /**
     * A secure random number generator for generating IDs.
     */
    public static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a random ID for objects.
     * 
     * This method generates a positive long value using a secure random number generator.
     * 
     * @return A randomly generated positive ID.
     */
    public default Long generateId() {
        return Math.abs(secureRandom.nextLong());
    }

    /**
     * Displays detailed information about a User.
     * 
     * This method prints out various attributes of the User object, including ID, name, email, and account status.
     * 
     * @param user The User object whose information is to be displayed.
     */
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
        System.out.println("Account non-expired: " + user.getAccountNonExpired());
        System.out.println("Account non-locked: " + user.getAccountNonLocked());
        System.out.println("Credentials non-expired: " + user.getCredentialsNonExpired());
        System.out.println("Enabled: " + user.getEnabled());
    }

}
