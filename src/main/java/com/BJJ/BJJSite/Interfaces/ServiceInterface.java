package com.BJJ.BJJSite.Interfaces;

import com.BJJ.BJJSite.Classes.User;

public interface ServiceInterface {

    /**
     * Generates a random ID for objects.
     * 
     * @return A randomly generated ID for.
     */
    public default String generateId() {
        return java.util.UUID.randomUUID().toString();
    }

    public default void displayInformation(User user) {
        System.out.println("User ID: " + user.getId());
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
