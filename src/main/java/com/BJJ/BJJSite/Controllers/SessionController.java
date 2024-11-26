package com.BJJ.BJJSite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import com.BJJ.BJJSite.Classes.Session;
import com.BJJ.BJJSite.Dto.SessionDto;
import com.BJJ.BJJSite.Dto.SessionResponseDto;
import com.BJJ.BJJSite.Repositories.SessionRepository;
import com.BJJ.BJJSite.Services.SessionService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@EnableMethodSecurity(prePostEnabled = true)
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<SessionResponseDto>> getSession(@PathVariable Integer id) {
        Session session = sessionRepository.getSessionById(id);

        SessionResponseDto sessionResponseDto = convertEntityToResponseDto(session);
        EntityModel<SessionResponseDto> resource = EntityModel.of(sessionResponseDto);

        // Adding HATEOAS links
        resource.add(linkTo(methodOn(SessionController.class).getSession(id)).withSelfRel());
        resource.add(linkTo(methodOn(SessionController.class).createSession(null)).withRel("create-session"));
        resource.add(linkTo(methodOn(SessionController.class).updateSession(id, null)).withRel("update-session"));
        resource.add(linkTo(methodOn(SessionController.class).deleteSession(id)).withRel("delete-session"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SessionResponseDto>> getAllSessions() {
        List<Session> sessions = sessionRepository.getAllSessions();
        List<SessionResponseDto> sessionDtos = sessions.stream()
                .map(this::convertEntityToResponseDto)
                .toList();

        return ResponseEntity.ok(sessionDtos);

    }

    @PostMapping("/addSession")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<SessionResponseDto>> createSession(@Valid @RequestBody SessionDto sessionDto) {
        
        System.out.println("Incoming DTO: " + sessionDto);
        
        Session createdSession = sessionService.createSession(sessionDto);
        SessionResponseDto sessionResponseDto = convertEntityToResponseDto(createdSession);

        EntityModel<SessionResponseDto> resource = EntityModel.of(sessionResponseDto);
        resource.add(linkTo(methodOn(SessionController.class).getSession(createdSession.getId())).withSelfRel());
        resource.add(linkTo(methodOn(SessionController.class).updateSession(createdSession.getId(), null))
                .withRel("update-session"));
        resource.add(linkTo(methodOn(SessionController.class).deleteSession(createdSession.getId()))
                .withRel("delete-session"));

        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EntityModel<SessionResponseDto>> updateSession(@PathVariable Integer id,
            @Valid SessionDto sessionDto) {
        Session updatedSession = sessionService.updateSession(id, sessionDto);

        SessionResponseDto sessionResponseDto = convertEntityToResponseDto(updatedSession);
        EntityModel<SessionResponseDto> resource = EntityModel.of(sessionResponseDto);

        resource.add(linkTo(methodOn(SessionController.class).getSession(updatedSession.getId())).withSelfRel());
        resource.add(linkTo(methodOn(SessionController.class).createSession(null)).withRel("create-session"));
        resource.add(linkTo(methodOn(SessionController.class).deleteSession(updatedSession.getId()))
                .withRel("delete-session"));

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id) {
        sessionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected SessionResponseDto convertEntityToResponseDto(Session session) {
        return SessionResponseDto.builder()
                .sessionId(session.getId())
                .dayOfWeek(session.getDayOfWeek())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .typeOfSession(session.getTypeOfSession())
                .build();
    }
}
