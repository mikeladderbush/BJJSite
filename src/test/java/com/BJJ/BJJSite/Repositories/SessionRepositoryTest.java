package com.BJJ.BJJSite.Repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.BJJ.BJJSite.Classes.Session;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session.SessionBuilder<>(1L, "Training", "10:00AM", new java.util.Date()).buildSession();
        sessionRepository.save(session);
    }

    @Test
    void testSaveSession() {
        // Arrange
        Session newSession = new Session.SessionBuilder<>(2L, "Seminar", "2:00PM", new java.util.Date()).buildSession();

        // Act
        Session savedSession = sessionRepository.save(newSession);

        // Assert
        assertNotNull(savedSession, "Session should be saved");
        assertEquals("Seminar", savedSession.getSessionType(), "Session type should match");
    }

    @Test
    void testFindAllSessions() {
        // Act
        List<Session> sessions = sessionRepository.findAll();

        // Assert
        assertFalse(sessions.isEmpty(), "Sessions list should not be empty");
        assertEquals(1, sessions.size(), "Sessions list size should be 1");
    }

    @Test
    void testFindByIdThrowsUnsupportedOperationException() {
        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> sessionRepository.findById(1L),
                "Calling findById should throw UnsupportedOperationException");
    }
}
