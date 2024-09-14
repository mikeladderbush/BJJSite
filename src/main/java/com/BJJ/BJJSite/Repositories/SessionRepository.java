package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

/**
 * Repository interface for managing Session entities.
 * 
 * This interface extends JpaRepository and is used for managing Session entities. Note that the `findById` method is overridden and currently not implemented, throwing an UnsupportedOperationException if called.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    /**
     * Finds a Session by its ID.
     * 
     * This method is overridden and currently unimplemented. If called, it will throw an UnsupportedOperationException.
     * 
     * @param id The ID of the Session.
     * @return An Optional containing the Session if found, or an exception is thrown.
     * @throws UnsupportedOperationException If this method is called.
     */
    @Override
    default Optional<Session> findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
