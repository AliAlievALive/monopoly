package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.service.GamerService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/gamer")
@RequiredArgsConstructor
public class GamerController {
    private final GamerService gamerService;

    @GetMapping("/list")
    public ResponseEntity<Response> getGamers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamers list limited 10")
                        .data(of("gamer", gamerService.getGamers(10)))
                        .build()
        );
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Response> getGamer(@PathVariable int id) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .statusCode(OK.value())
                            .status(OK)
                            .message("Gamer with " + id + " is found")
                            .data(of("gamer", gamerService.getGamer(id)))
                            .build()
            );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveGamer(@RequestBody Gamer gamer) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer " + gamer.getName() + " is save")
                        .data(of("gamer", gamerService.create(gamer)))
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody Gamer gamer) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with id " + gamer.getId() + " is update")
                        .data(of("gamer", gamerService.update(gamer)))
                        .build()
        );
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Response> deleteGamer(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with id " + id + " is deleted")
                        .data(of("gamer", gamerService.delete(id)))
                        .build()
        );
    }
}