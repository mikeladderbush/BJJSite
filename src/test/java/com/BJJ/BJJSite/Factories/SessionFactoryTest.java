package com.BJJ.BJJSite.Factories;

import com.BJJ.BJJSite.Classes.Session;
import com.BJJ.BJJSite.Services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionFactoryTest {

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSessionWithDefaultValues() {
        when(sessionService.generateId()).thenReturn(1);

        Session result = SessionFactory.createSession();

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("REGULAR CLASS", result.getSessionType());
        assertEquals("6:00PM", result.getTimeOfSession());
        assertNotNull(result.getDateOfSession());
        verify(sessionService, times(1)).generateId();
    }
}
