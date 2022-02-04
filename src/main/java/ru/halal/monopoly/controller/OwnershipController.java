package ru.halal.monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.domain.ownerships.Ownership;
import ru.halal.monopoly.service.OwnershipService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership")
public record OwnershipController(OwnershipService ownershipService) {
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

    @GetMapping({"{id}"})
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
    public ResponseEntity<Response> saveOwnership(@RequestBody Ownership ownership) {
        if (ownership instanceof Communal) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .statusCode(OK.value())
                            .status(OK)
                            .message("Ownership " + ownership.getName() + " is save")
                            .data(of("ownership", ownershipService.create(ownership)))
                            .build()
            );
        }
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership " + ownership.getName() + " is save")
                        .data(of("ownership", ownershipService.create(ownership)))
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateOwnership(@RequestBody Ownership ownership) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Ownership with id " + ownership.getId() + " is updated")
                        .data(of("ownership", ownershipService.update(ownership)))
                        .build()
        );
    }

    @DeleteMapping("{id}")
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
