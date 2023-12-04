package ru.scooterrent.crud.models;

public record Scooter(Long scooterId, String brandName, String model, int maxSpeed, boolean isActive) {
}
