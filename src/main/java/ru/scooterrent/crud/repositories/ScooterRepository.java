package ru.scooterrent.crud.repositories;

import ru.scooterrent.crud.models.Scooter;

import java.util.List;

public interface ScooterRepository {
    List<Scooter> readAll();
    Scooter read(Long scooterId);
    void save(Scooter scooter);
    void update(Long scooterId, Scooter scooter);
    void delete(Long scooterId);
}
