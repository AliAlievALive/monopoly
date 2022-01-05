package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.service.GamerService;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/gamer")
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
                        .data(of("gamersList", gamerService.getGamers(10)))
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
    public ResponseEntity<Gamer> saveGamer(@RequestBody Gamer gamer) {
        gamerService.create(gamer);
        return ResponseEntity.ok(
                Gamer.builder()
                        .id(gamer.getId())
                        .name(gamer.getName())
                        .money(gamer.getMoney())
                        .cities(gamer.getCities())
                        .communal(gamer.getCommunal())
                        .airports(gamer.getAirports())
                        .build()
        );
    }

    @PutMapping("/save")
    public ResponseEntity<Gamer> update(@RequestBody Gamer gamer) {
        gamerService.update(gamer);
        return ResponseEntity.ok(
                Gamer.builder()
                        .name(gamer.getName())
                        .money(gamer.getMoney())
                        .cities(gamer.getCities())
                        .communal(gamer.getCommunal())
                        .airports(gamer.getAirports())
                        .build()
        );
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<Gamer> deleteGamer(@RequestBody Gamer gamer) {
        gamerService.delete(gamer);
        return ResponseEntity.ok(
                Gamer.builder()
                        .name(gamer.getName())
                        .money(gamer.getMoney())
                        .cities(gamer.getCities())
                        .communal(gamer.getCommunal())
                        .airports(gamer.getAirports())
                        .build()
        );
    }
}
