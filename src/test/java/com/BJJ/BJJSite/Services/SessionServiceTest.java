package com.BJJ.BJJSite.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.BJJ.BJJSite.Classes.Session;

class SessionServiceTest {

    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService();
    }

    @Test
    void testAddSession() {
        Session session = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();

        sessionService.addSession(session);
        List<Session> sessions = sessionService.getAllSessions();

        assertEquals(1, sessions.size());
        assertEquals(session, sessions.get(0));
    }

    @Test
    void testDeleteSession_SessionExists() {
        Session session1 = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();
        Session session2 = new Session.SessionBuilder<>(2L, "Seminar", "2:00 PM", new java.util.Date()).buildSession();

        sessionService.addSession(session1);
        sessionService.addSession(session2);

        sessionService.deleteSession(1L);
        List<Session> sessions = sessionService.getAllSessions();

        assertEquals(1, sessions.size());
        assertEquals(session2, sessions.get(0));
    }

    @Test
    void testDeleteSession_SessionDoesNotExist() {
        Session session1 = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();
        sessionService.addSession(session1);

        sessionService.deleteSession(2L);
        List<Session> sessions = sessionService.getAllSessions();

        assertEquals(1, sessions.size());
        assertEquals(session1, sessions.get(0));
    }

    @Test
    void testGetAllSessions() {
        Session session1 = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();
        Session session2 = new Session.SessionBuilder<>(2L, "Seminar", "2:00 PM", new java.util.Date()).buildSession();

        sessionService.addSession(session1);
        sessionService.addSession(session2);

        List<Session> sessions = sessionService.getAllSessions();

        assertEquals(2, sessions.size());
        assertTrue(sessions.contains(session1));
        assertTrue(sessions.contains(session2));
    }

    @Test
    void testFindSessionById_SessionExists() {
        Session session = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();

        sessionService.addSession(session);

        Session foundSession = sessionService.findSessionById(1L);

        assertNotNull(foundSession);
        assertEquals(session, foundSession);
    }

    @Test
    void testFindSessionById_SessionDoesNotExist() {
        Session session = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new java.util.Date()).buildSession();

        sessionService.addSession(session);

        Session foundSession = sessionService.findSessionById(2L);

        assertNull(foundSession);
    }

    @Test
    void testGenerateId() {
        Long id1 = sessionService.generateId();
        Long id2 = sessionService.generateId();

        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
    }
}
