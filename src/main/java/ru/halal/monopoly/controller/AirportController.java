package ru.halal.monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.service.AirportService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership/airports")
public record AirportController(AirportService airportService) {
    @GetMapping
    public ResponseEntity<Response> getAirportList() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Airport list limited 4")
                        .data(of("airport", airportService.getAirportList(4)))
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getAirport(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Airport with " + id + " is found")
                        .data(of("airport", airportService.getAirport(id)))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> saveAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Airport " + airport.getName() + " is save")
                        .data(of("airport", airportService.create(airport)))
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Airport with id " + airport.getId() + " is updated")
                        .data(of("airport", airportService.update(airport)))
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteAirport(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Airport with id " + id + " is deleted")
                        .data(of("airport", airportService.delete(id)))
                        .build()
        );
    }
}
