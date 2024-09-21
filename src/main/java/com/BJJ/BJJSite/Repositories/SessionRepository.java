package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Repository interface for managing Session entities.
 * 
 * This interface extends JpaRepository but disables the default findById method.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Logger logger = LoggerFactory.getLogger(SessionRepository.class);

    /**
     * Disabled method that would normally find a Session by its ID.
     * 
     * This method is intentionally unimplemented. If called, it will log a warning
     * and throw an UnsupportedOperationException.
     * 
     * @param id The ID of the Session.
     * @return An UnsupportedOperationException is thrown.
     * @throws UnsupportedOperationException If this method is called.
     */
    @Override
    default Optional<Session> findById(Integer id) {
        logger.warn("findById method is disabled for SessionRepository");
        throw new UnsupportedOperationException("The findById method is disabled for SessionRepository");
    }
}
