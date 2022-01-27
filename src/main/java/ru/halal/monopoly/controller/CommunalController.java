package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.service.CommunalService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership/communal")
@RequiredArgsConstructor
public class CommunalController {
    private final CommunalService communalService;

    @GetMapping
    public ResponseEntity<Response> getCommunalList() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Communal list limited 10")
                        .data(of("communal", communalService.getCommunalList(2)))
                        .build()
        );
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Response> getCommunal(@PathVariable int id) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .statusCode(OK.value())
                            .status(OK)
                            .message("Communal with " + id + " is found")
                            .data(of("communal", communalService.getCommunal(id)))
                            .build()
            );
    }

    @PostMapping
    public ResponseEntity<Response> saveCommunal(@RequestBody Communal communal) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Communal " + communal.getName() + " is save")
                        .data(of("communal", communalService.create(communal)))
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateCommunal(@RequestBody Communal communal) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Communal with id " + communal.getId() + " is updated")
                        .data(of("communal", communalService.update(communal)))
                        .build()
        );
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Response> deleteCommunal(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Communal with id " + id + " is deleted")
                        .data(of("communal", communalService.delete(id)))
                        .build()
        );
    }
}
