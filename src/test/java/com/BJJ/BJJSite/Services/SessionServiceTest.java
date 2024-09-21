package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Classes.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {

    private SessionService sessionService;
    private Session mockSession1;
    private Session mockSession2;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService();

        // Create mock sessions
        mockSession1 = new Session.SessionBuilder<>(1, "Training", "10:00 AM", new Date()).buildSession();
        mockSession2 = new Session.SessionBuilder<>(2, "Seminar", "2:00 PM", new Date()).buildSession();

        // Add mock sessions to the sessionService
        sessionService.addSession(mockSession1);
        sessionService.addSession(mockSession2);
    }

    @Test
    void testAddSession() {
        Session newSession = new Session.SessionBuilder<>(3, "Workshop", "4:00 PM", new Date()).buildSession();
        sessionService.addSession(newSession);

        List<Session> allSessions = sessionService.getAllSessions();
        assertEquals(3, allSessions.size());
        assertTrue(allSessions.contains(newSession));
    }

    @Test
    void testDeleteSession() {
        sessionService.deleteSession(1L);

        List<Session> allSessions = sessionService.getAllSessions();
        assertEquals(1, allSessions.size());
        assertFalse(allSessions.contains(mockSession1));
    }

    @Test
    void testGetAllSessions() {
        List<Session> allSessions = sessionService.getAllSessions();

        assertEquals(2, allSessions.size());
        assertTrue(allSessions.contains(mockSession1));
        assertTrue(allSessions.contains(mockSession2));
    }

    @Test
    void testFindSessionById_Success() {
        Session foundSession = sessionService.findSessionById(1L);

        assertNotNull(foundSession);
        assertEquals("Training", foundSession.getSessionType());
    }

    @Test
    void testFindSessionById_NotFound() {
        Session foundSession = sessionService.findSessionById(999L);

        assertNull(foundSession);
    }

    @Test
    void testGenerateId() {
        Integer id = sessionService.generateId();
        assertNotNull(id);
        assertTrue(id > 0);
    }
}
