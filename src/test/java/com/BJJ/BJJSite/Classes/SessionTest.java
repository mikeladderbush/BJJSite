package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionTest {

    private Session.SessionBuilder<?> builder;

    @BeforeEach
    void setUp() {
        builder = new Session.SessionBuilder<>(1L, "Training", "10:00 AM", new Date());
    }

    @Test
    void testSessionCreation() {
        Session session = builder.buildSession();

        assertNotNull(session);
        assertEquals(1L, session.getId());
        assertEquals("Training", session.getSessionType());
        assertEquals("10:00 AM", session.getTimeOfSession());
        assertNotNull(session.getDateOfSession());
    }

    @Test
    void testSettersAndGetters() {
        Session session = builder.buildSession();

        session.setId(2L);
        session.setSessionType("Seminar");
        session.setTimeOfSession("2:00 PM");
        Date newDate = new Date();
        session.setDateOfSession(newDate);

        assertEquals(2L, session.getId());
        assertEquals("Seminar", session.getSessionType());
        assertEquals("2:00 PM", session.getTimeOfSession());
        assertEquals(newDate, session.getDateOfSession());
    }

    @Test
    void testDefaultConstructor() {
        Session session = new Session();
        
        assertNotNull(session);
        // The attributes should be null or their default values if not set explicitly.
        assertEquals(null, session.getId());
        assertEquals(null, session.getSessionType());
        assertEquals(null, session.getTimeOfSession());
        assertEquals(null, session.getDateOfSession());
    }
}
