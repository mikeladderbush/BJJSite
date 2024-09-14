package com.BJJ.BJJSite.Classes;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Training Session.
 * 
 * The Session class is an entity that represents a training session, including details such as the session type, time, and date.
 */
@Entity
@Table(name = "sessions")
public class Session {

    /**
     * Default constructor.
     */
    public Session() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String sessionType;

    @Column(nullable = false)
    private String timeOfSession;

    @Column(nullable = false)
    private Date dateOfSession;

    /**
     * Protected constructor to be called by the SessionBuilder.
     * 
     * @param SessionBuilder The builder used to construct the Session instance.
     */
    protected Session(SessionBuilder<?> SessionBuilder) {
        id = SessionBuilder.id;
        sessionType = SessionBuilder.sessionType;
        timeOfSession = SessionBuilder.timeOfSession;
        dateOfSession = SessionBuilder.dateOfSession;
    }

    /**
     * Builder class for creating a Session instance.
     * 
     * This builder pattern allows for flexible and customizable creation of Session objects.
     */
    public static class SessionBuilder<T extends SessionBuilder<T>> {

        private Long id;
        private String sessionType;
        private String timeOfSession;
        private Date dateOfSession;

        /**
         * Constructor for SessionBuilder.
         * 
         * @param id The ID of the session.
         * @param sessionType The type of session (e.g., "Training", "Seminar").
         * @param timeOfSession The time of the session.
         * @param dateOfSession The date of the session.
         */
        public SessionBuilder(Long id, String sessionType, String timeOfSession, Date dateOfSession) {
            this.id = id;
            this.sessionType = sessionType;
            this.timeOfSession = timeOfSession;
            this.dateOfSession = dateOfSession;
        }

        /**
         * Ensures that the builder returns the correct type for method chaining.
         * 
         * @return The current instance of SessionBuilder.
         */
        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        /**
         * Builds and returns a Session instance.
         * 
         * @return A new Session object.
         */
        public Session buildSession() {
            return new Session(this);
        }
    }

    /**
     * Retrieves the ID of the Session.
     * 
     * @return The ID of the Session.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the Session.
     * 
     * This method is primarily intended for testing purposes.
     * 
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the type of the Session.
     * 
     * @return The session type.
     */
    public String getSessionType() {
        return sessionType;
    }

    /**
     * Sets the type of the Session.
     * 
     * @param sessionType The type of session to set.
     */
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    /**
     * Retrieves the time of the Session.
     * 
     * @return The time of the session.
     */
    public String getTimeOfSession() {
        return timeOfSession;
    }

    /**
     * Sets the time of the Session.
     * 
     * @param timeOfSession The time of session to set.
     */
    public void setTimeOfSession(String timeOfSession) {
        this.timeOfSession = timeOfSession;
    }

    /**
     * Retrieves the date of the Session.
     * 
     * @return The date of the session.
     */
    public Date getDateOfSession() {
        return dateOfSession;
    }

    /**
     * Sets the date of the Session.
     * 
     * @param dateOfSession The date of session to set.
     */
    public void setDateOfSession(Date dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

}
