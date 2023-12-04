package ru.scooterrent.crud.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.scooterrent.crud.models.Scooter;
import ru.scooterrent.crud.repositories.ScooterRepository;

import java.util.List;

@RestController
@RequestMapping("api/scooters")
public class ScooterController {
    private final ScooterRepository scooterRepository;

    public ScooterController(ScooterRepository scooterRepository) {
        this.scooterRepository = scooterRepository;
    }

    @GetMapping
    public List<Scooter> getScooter() {
        return scooterRepository.readAll();
    }

    @GetMapping("/{scooter_id}")
    public Scooter getScooter(@PathVariable("scooter_id") Long scooterId) {
        return scooterRepository.read(scooterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addScooter(@RequestParam("brand") String brand,
                           @RequestParam("model") String model,
                           @RequestParam("maxSpeed") int maxSpeed) {
        Scooter newScooter = new Scooter(null, brand, model, maxSpeed, true);
        scooterRepository.save(newScooter);
    }

    @PutMapping("/{scooter_id}")
    public void updateScooter(
            @PathVariable("scooter_id") Long scooterId,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("maxSpeed") int maxSpeed,
            @RequestParam("active") boolean active) {

        Scooter updatedScooter = new Scooter(
                scooterId,
                brand,
                model,
                maxSpeed,
                active
        );
        scooterRepository.update(scooterId, updatedScooter);
    }

    @DeleteMapping("/{scooter_id}")
    public void deleteScooter(@PathVariable("scooter_id") Long scooterId) {
        scooterRepository.delete(scooterId);
    }
}
