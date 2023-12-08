package ru.scooterrent.crud.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.scooterrent.crud.models.Session;
import ru.scooterrent.crud.repositories.SessionRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/scooters/{scooter_id}/sessions")
public class SessionController {

    private final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping()
    public List<Session> getSession(@PathVariable("scooter_id") Long scooterId) {
        return sessionRepository.readByScooterId(scooterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSession(@PathVariable("scooter_id") Long scooterId,
                           @RequestParam("start") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date dateStart,
                           @RequestParam("end") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date dateEnd) {
        Session newSession = new Session(null, scooterId, dateStart, dateEnd);
        sessionRepository.save(newSession);
    }

    @PutMapping("/{session_id}")
    public void updateSession(@PathVariable("session_id") Long sessionId,
                                @RequestParam("scooter_id") Long scooterId,
                                @RequestParam("start") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date dateStart,
                                @RequestParam("end") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date dateEnd) {
        Session updatedSession = new Session(
                sessionId,
                scooterId,
                dateStart,
                dateEnd
        );
        sessionRepository.update(sessionId, updatedSession);
    }

    @DeleteMapping("/{session_id}")
    public void deleteSession(@PathVariable("session_id") Long sessionId) {
        sessionRepository.delete(sessionId);
    }
}
