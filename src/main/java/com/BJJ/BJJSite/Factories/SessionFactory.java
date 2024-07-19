package com.BJJ.BJJSite.Factories;

import java.util.Date;
import java.util.function.Consumer;

import com.BJJ.BJJSite.Classes.Session;
import com.BJJ.BJJSite.Services.SessionService;

public class SessionFactory {

    private static final SessionService sessionService = new SessionService();

    // Method to create a Session with default values
    public static Session createSession() {
        String id = sessionService.generateId();
        String sessionType = "REGULAR CLASS";
        String timeOfSession = "6:00PM";
        Date dateOfSession = new Date();
        return new Session.SessionBuilder<>(id, sessionType, timeOfSession, dateOfSession).buildSession();
    }

    public static Session createSession(Consumer<Session.SessionBuilder<?>> consumer) {
        String id = sessionService.generateId();
        String sessionType = "REGULAR CLASS";
        String timeOfSession = "6:00PM";
        Date dateOfSession = new Date();
        Session.SessionBuilder<?> builder = new Session.SessionBuilder<>(id, sessionType, timeOfSession, dateOfSession);
        consumer.accept(builder);
        return builder.buildSession();
    }
}
