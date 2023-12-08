package ru.scooterrent.crud.repositories;

import ru.scooterrent.crud.models.Scooter;
import ru.scooterrent.crud.models.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> readByScooterId(Long scooterId);
    void save(Session session);
    void update(Long sessionId, Session session);
    void delete(Long sessionId);
}
