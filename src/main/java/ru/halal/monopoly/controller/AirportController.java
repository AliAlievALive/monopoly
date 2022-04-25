package ru.halal.monopoly.controller;

import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.service.AirportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/ownership/airports")
public record AirportController(AirportService airportService) {
    @GetMapping
    public Collection<Airport> getAirportList() {
        return airportService.getAirportList(4);
    }

    @GetMapping("{id}")
    public Airport getAirport(@PathVariable int id) {
        return airportService.getAirport(id);
    }

    @PostMapping
    public void saveAirport(@RequestBody Airport airport) {
        airportService.create(airport);
    }

    @PutMapping
    public Airport updateAirport(@RequestBody Airport airport) {
        return airportService.update(airport);
    }

    @DeleteMapping("{id}")
    public Boolean deleteAirport(@PathVariable int id) {
        return airportService.delete(id);
    }
}
