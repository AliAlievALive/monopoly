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

    @GetMapping
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

    @PostMapping
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

    @PutMapping
    public ResponseEntity<Response> updateGamer(@RequestBody Gamer gamer) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with id " + gamer.getId() + " is updated")
                        .data(of("gamer", gamerService.update(gamer)))
                        .build()
        );
    }

    @DeleteMapping ("/{id}")
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

    @GetMapping({"/add_city_to_gamer/{cityId}/{gamerId}"})
    public ResponseEntity<Response> addCityToGamer(@PathVariable int cityId, @PathVariable int gamerId) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("To gamer with id " + gamerId + " is added city with id " + cityId)
                        .data(of("gamer", gamerService.addCityToGamer(cityId, gamerId)))
                        .build()
        );
    }

    @GetMapping({"/city_to_another_gamer/{fromId}/{toId}/{cityId}"})
    public ResponseEntity<Response> cityToAnotherGamer(@PathVariable int fromId, @PathVariable int toId, @PathVariable int cityId) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with id " + fromId +
                                " give city with id " + cityId +
                                " to gamer with id " + toId)
                        .data(of("gamer", gamerService.giveCityToAnotherGamer(fromId, toId, cityId)))
                        .build()
        );
    }

    @GetMapping({"/money/{id}/{money}"})
    public ResponseEntity<Response> cityToAnotherGamer(@PathVariable int id, @PathVariable int money) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamers money with id " + id + " change to " + money + " money")
                        .data(of("gamer", gamerService.changeMoneyCounts(id, money)))
                        .build()
        );
    }
}
