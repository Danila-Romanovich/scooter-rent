package ru.scooterrent.crud.services;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.scooterrent.crud.models.Scooter;
import ru.scooterrent.crud.models.Session;
import ru.scooterrent.crud.repositories.SessionRepository;

import java.util.List;

@Repository
public class SessionService implements SessionRepository {

    private final RowMapper<Session> rowMapper = new DataClassRowMapper<>(Session.class);
    private final JdbcTemplate jdbcTemplate;

    public SessionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Session> readByScooterId(Long scooterId) {
        return jdbcTemplate.query("SELECT * FROM session WHERE scooterId = ?", rowMapper, scooterId);
    }

    @Override
    public void save(Session session) {
        jdbcTemplate.update("INSERT INTO session(scooterId, dateStart, dateEnd) VALUES(?, ?, ?)",
                session.scooterId(), session.dateStart(), session.dateEnd());
    }

    @Override
    public void update(Long sessionId, Session session) {
        jdbcTemplate.update("UPDATE session SET scooterId=?, dateStart=?, dateEnd=? WHERE sessionId=?",
                session.scooterId(), session.dateStart(), session.dateEnd(), sessionId);
    }

    @Override
    public void delete(Long sessionId) {
        jdbcTemplate.update("DELETE FROM session WHERE sessionId=?", sessionId);
    }
}
