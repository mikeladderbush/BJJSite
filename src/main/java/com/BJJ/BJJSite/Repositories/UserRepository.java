package com.BJJ.BJJSite.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserById(Integer id);

    User getUserByEmail(String email);

    void deleteUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
