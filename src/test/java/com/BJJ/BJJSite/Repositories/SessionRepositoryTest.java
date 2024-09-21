package com.BJJ.BJJSite.Repositories;

import com.BJJ.BJJSite.Classes.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session.SessionBuilder<>(1, "Training", "10:00 AM", new Date()).buildSession();
        sessionRepository.save(session);
    }

    @Test
    void testFindById_Success() {
        Optional<Session> foundSession = sessionRepository.findById(session.getId());

        assertTrue(foundSession.isPresent());
        assertEquals(session.getId(), foundSession.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        Optional<Session> foundSession = sessionRepository.findById(999);

        assertFalse(foundSession.isPresent());
    }
}
