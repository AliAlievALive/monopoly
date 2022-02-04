package ru.halal.monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.*;
import ru.halal.monopoly.service.AirportService;
import ru.halal.monopoly.service.CityService;
import ru.halal.monopoly.service.CommunalService;
import ru.halal.monopoly.service.OwnershipService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership")
public record OwnershipController(CityService cityService,
                                  CommunalService communalService,
                                  AirportService airportService,
                                  OwnershipService ownershipService) {
    @GetMapping
    public ResponseEntity<Response> getOwnerships() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership list limited 10")
                        .data(of("ownership", ownershipService.getOwnerships(10)))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOwnership(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership with " + id + " is found")
                        .data(of("ownership", ownershipService.getOwnership(id)))
                        .build()
        );
    }

    @PostMapping
    public <T extends Ownership> ResponseEntity<Response> saveOwnership(@RequestBody T ownership) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .statusCode(OK.value())
                            .status(OK)
                            .message("Ownership " + ownership.getName() + " is save")
                            .data(of("ownership", ownershipService().createOrUpdate(ownership)))
                            .build()
            );
    }

    @PutMapping
    public <T extends Ownership> ResponseEntity<Response> updateOwnership(@RequestBody T ownership) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership with id " + ownership.getId() + " is updated")
                        .data(of("ownership", ownershipService.createOrUpdate(ownership)))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteOwnership(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership with id " + id + " is deleted")
                        .data(of("ownership", ownershipService.delete(id)))
                        .build()
        );
    }
}
