package ru.scooterrent.crud.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.scooterrent.crud.exceptions.NotFoundException;
import ru.scooterrent.crud.models.Scooter;
import ru.scooterrent.crud.repositories.ScooterRepository;

import java.util.List;

@Repository
public class ScooterService implements ScooterRepository {

    private final RowMapper<Scooter> rowMapper = new DataClassRowMapper<>(Scooter.class);
    private final JdbcTemplate jdbcTemplate;

    public ScooterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Scooter> readAll() {
        return jdbcTemplate.query("SELECT * FROM scooter", rowMapper);
    }

    @Override
    public Scooter read(Long scooterId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM scooter WHERE scooterid=?", rowMapper, scooterId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Scooter with id = [" + scooterId + "] not found", e);
        }
    }

    @Override
    public void save(Scooter scooter) {
        jdbcTemplate.update("INSERT INTO scooter(brandName, model, maxSpeed) VALUES(?, ?, ?)", scooter.brandName(), scooter.model(), scooter.maxSpeed());
    }

    @Override
    public void update(Long scooterId, Scooter scooter) {
        try {
            jdbcTemplate.update("UPDATE scooter SET brandName=?, model=?, maxSpeed=?, isActive=? WHERE scooterid=?",
                    scooter.brandName(), scooter.model(), scooter.maxSpeed(), scooter.isActive(), scooterId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Scooter with id = [" + scooterId + "] not found", e);
        }
    }

    @Override
    public void delete(Long scooterId) {
        try {
            jdbcTemplate.update("DELETE FROM scooter WHERE scooterid=?", scooterId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Scooter with id = [" + scooterId + "] not found", e);
        }
    }
}
