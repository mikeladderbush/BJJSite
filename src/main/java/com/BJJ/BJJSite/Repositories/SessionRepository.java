package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Override
    default Optional<Session> findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
