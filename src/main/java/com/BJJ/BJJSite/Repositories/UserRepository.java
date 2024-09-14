package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a User by their username.
     * 
     * @param username The username of the User.
     * @return An Optional containing the User if found, or an empty Optional if not found.
     */
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
