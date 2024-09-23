package com.BJJ.BJJSite.Repositories;

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

    User getUserById(Integer id);

    User getUserByEmail(String email);

    User deleteUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
