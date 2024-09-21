package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class SessionTest {

    private Session session;
    private Date sessionDate;

    @BeforeEach
    void setUp() {
        sessionDate = new Date(); // current date for testing
        session = new Session.SessionBuilder<>(1, "Training", "10:00 AM", sessionDate)
                .buildSession();
    }

    @Test
    void testGetId() {
        assertEquals(1, session.getId());
    }

    @Test
    void testSetId() {
        session.setId(2);
        assertEquals(2, session.getId());
    }

    @Test
    void testGetSessionType() {
        assertEquals("Training", session.getSessionType());
    }

    @Test
    void testSetSessionType() {
        session.setSessionType("Seminar");
        assertEquals("Seminar", session.getSessionType());
    }

    @Test
    void testGetTimeOfSession() {
        assertEquals("10:00 AM", session.getTimeOfSession());
    }

    @Test
    void testSetTimeOfSession() {
        session.setTimeOfSession("11:00 AM");
        assertEquals("11:00 AM", session.getTimeOfSession());
    }

    @Test
    void testGetDateOfSession() {
        assertEquals(sessionDate, session.getDateOfSession());
    }

    @Test
    void testSetDateOfSession() {
        Date newDate = new Date();
        session.setDateOfSession(newDate);
        assertEquals(newDate, session.getDateOfSession());
    }

    @Test
    void testBuilderPattern() {
        Session newSession = new Session.SessionBuilder<>(2, "Seminar", "11:00 AM", new Date())
                .buildSession();
        assertEquals(2, newSession.getId());
        assertEquals("Seminar", newSession.getSessionType());
        assertEquals("11:00 AM", newSession.getTimeOfSession());
        assertNotNull(newSession.getDateOfSession());
    }
}
