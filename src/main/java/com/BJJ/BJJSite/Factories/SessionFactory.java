package com.BJJ.BJJSite.Factories;

import java.util.Date;
import java.util.function.Consumer;

import com.BJJ.BJJSite.Classes.Session;
import com.BJJ.BJJSite.Services.SessionService;

/**
 * Factory class for creating instances of Session.
 * 
 * This factory provides methods to create Session objects with default values or with custom configurations.
 */
public class SessionFactory {

    private static final SessionService sessionService = new SessionService();

    /**
     * Creates a Session with default values.
     * 
     * The default values include a generated ID, session type "REGULAR CLASS", time "6:00PM", and the current date.
     * 
     * @return A new Session object with default values.
     */
    public static Session createSession() {
        Integer id = sessionService.generateId();
        String sessionType = "REGULAR CLASS";
        String timeOfSession = "6:00PM";
        Date dateOfSession = new Date();
        return new Session.SessionBuilder<>(id, sessionType, timeOfSession, dateOfSession).buildSession();
    }

    /**
     * Creates a Session with custom values using a Consumer to configure the SessionBuilder.
     * 
     * This method allows for the creation of a Session with default values that can be overridden by the provided Consumer.
     * 
     * @param consumer A Consumer that applies custom configurations to the SessionBuilder.
     * @return A new Session object with custom values.
     */
    public static Session createSession(Consumer<Session.SessionBuilder<?>> consumer) {
        Integer id = sessionService.generateId();
        String sessionType = "REGULAR CLASS";
        String timeOfSession = "6:00PM";
        Date dateOfSession = new Date();
        Session.SessionBuilder<?> builder = new Session.SessionBuilder<>(id, sessionType, timeOfSession, dateOfSession);
        consumer.accept(builder);
        return builder.buildSession();
    }
}
