package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.Session;
import com.BJJ.BJJSite.Dto.SessionDto;
import com.BJJ.BJJSite.Exceptions.SessionAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.SessionRepository;

import jakarta.transaction.Transactional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session createSession(SessionDto sessionDto) {

        if (sessionRepository.isOverlappingSession(sessionDto.getDayOfWeek(), sessionDto.getStartTime(),
                sessionDto.getEndTime())) {
            throw new SessionAlreadyExistsException("Session already exists for this day and time.");
        }

        Session session = convertDtoToEntity(sessionDto);
        session.setStartTime(sessionDto.getStartTime());
        session.setEndTime(sessionDto.getEndTime());

        return sessionRepository.save(session);
    }

    @Transactional
    public Session updateSession(Integer id, SessionDto sessionDto) {
        if (id == null || sessionDto == null) {
            throw new IllegalArgumentException("Need a valid session id and sessionDto to update");
        }

        Session oldSession = sessionRepository.getSessionById(id);
        updateEntityFromDto(oldSession, sessionDto);
        return sessionRepository.getSessionById(id);
    }

    @Transactional
    public void deleteSession(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id for deletion");
        }

        sessionRepository.deleteById(id);
    }

    private void updateEntityFromDto(Session session, SessionDto sessionDto) {
        session.setDayOfWeek(sessionDto.getDayOfWeek());
        session.setStartTime(sessionDto.getStartTime());
        session.setEndTime(sessionDto.getEndTime());
        session.setTypeOfSession(sessionDto.getTypeOfSession());
    }

    private Session convertDtoToEntity(SessionDto sessionDto) {
        return Session.builder()
                .dayOfWeek(sessionDto.getDayOfWeek())
                .startTime(sessionDto.getStartTime())
                .endTime(sessionDto.getEndTime())
                .typeOfSession(sessionDto.getTypeOfSession())
                .build();
    }

}
