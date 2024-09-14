package com.BJJ.BJJSite.Classes;

/**
 * Represents a security role in the system.
 * 
 * The Role class implements the Spring Security `GrantedAuthority` interface 
 * and is used to define the roles and authorities assigned to users within 
 * the application.
 */
public enum Role {
   USER,
   ADMIN
}
