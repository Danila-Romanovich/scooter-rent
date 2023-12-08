package ru.scooterrent.crud.models;

import java.util.Date;
public record Session(Long sessionId, Long scooterId, Date dateStart, Date dateEnd) {
}
