package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.User;

/**
 * Repository interface for managing User entities.
 * 
 * This interface extends JpaRepository and provides methods to retrieve User
 * entities by various attributes such as username and email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a User by their username.
     * 
     * @param username The username of the User.
     * @return An Optional containing the User if found, or an empty Optional if not
     *         found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a User by their email address.
     * 
     * @param email The email address of the User.
     * @return An Optional containing the User if found, or an empty Optional if not
     *         found.
     */
    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer integer);

    boolean existsByEmail(String email);

}
