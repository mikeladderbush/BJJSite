package com.BJJ.BJJSite.Classes;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a security role in the system.
 * 
 * The Role class implements the Spring Security `GrantedAuthority` interface 
 * and is used to define the roles and authorities assigned to users within 
 * the application.
 */
@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {
    
    /**
     * The unique identifier for the Role.
     * 
     * This is the primary key and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the authority/role (e.g., "ROLE_ADMIN", "ROLE_USER").
     */
    @Column(nullable = false)
    private String authority;

    /**
     * Default constructor required by JPA.
     */
    public Role() {
    }

    /**
     * Constructor to create a Role with a specific authority.
     * 
     * @param authority The name of the authority/role.
     */
    public Role(String authority) {
        this.authority = authority;
    }

    /**
     * Returns the authority granted by this role.
     * 
     * This method is required by the `GrantedAuthority` interface.
     * 
     * @return The name of the authority/role.
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
