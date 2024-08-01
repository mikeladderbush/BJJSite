package com.BJJ.BJJSite.Classes;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Training Session.
 */
@Entity
@Table(name = "sessions")
public class Session {

    public Session() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String sessionType;

    // Eventually set to a date/time that works best with database.
    private String timeOfSession;
    private Date dateOfSession;

    protected Session(SessionBuilder<?> SessionBuilder) {
        id = SessionBuilder.id;
        sessionType = SessionBuilder.sessionType;
        timeOfSession = SessionBuilder.timeOfSession;
        dateOfSession = SessionBuilder.dateOfSession;
    }

    public static class SessionBuilder<T extends SessionBuilder<T>> {

        private Long id;
        private String sessionType;
        private String timeOfSession;
        private Date dateOfSession;

        public SessionBuilder(Long id, String sessionType, String timeOfSession, Date dateOfSession) {
            this.id = id;
            this.sessionType = sessionType;
            this.timeOfSession = timeOfSession;
            this.dateOfSession = dateOfSession;
        }

        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

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
     * For testing purpose only.
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getTimeOfSession() {
        return timeOfSession;
    }

    public void setTimeOfSession(String timeOfSession) {
        this.timeOfSession = timeOfSession;
    }

    public Date getDateOfSession() {
        return dateOfSession;
    }

    public void setDateOfSession(Date dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

}
