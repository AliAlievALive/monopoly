package ru.halal.monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.EType;
import ru.halal.monopoly.domain.ownerships.TypeWrapper;
import ru.halal.monopoly.service.GamerService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/gamers")
public record GamerController(GamerService gamerService) {

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

    @GetMapping({"{id}"})
    public ResponseEntity<Response> getGamer(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with " + id + " is found")
                        .data(of("gamer", gamerService.getGamerById(id)))
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

    @DeleteMapping("{id}")
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

    @PostMapping({"/add_own_to_gamer/{ownId}/{gamerId}"})
    public ResponseEntity<Response> addOwnToGamer(@PathVariable int ownId,
                                                  @PathVariable int gamerId,
                                                  @RequestBody TypeWrapper type) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("To gamer with id " + gamerId + " is added ownership with id " + ownId)
                        .data(of("gamer", gamerService.addOwnToGamer(ownId, gamerId, type)))
                        .build()
        );
    }

    @PostMapping({"/give_to_another_gamer/{fromId}/{toId}/{ownId}"})
    public ResponseEntity<Response> ownToAnotherGamer(@RequestBody TypeWrapper type, @PathVariable int fromId, @PathVariable int toId, @PathVariable int ownId) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Gamer with id " + fromId +
                                " give own with id " + ownId +
                                " to gamer with id " + toId)
                        .data(of("gamer", gamerService.giveOwnToAnotherGamer(type, fromId, toId, ownId)))
                        .build()
        );
    }

    @GetMapping({"/money/{id}/{money}"})
    public ResponseEntity<Response> moneyToGamer(@PathVariable int id, @PathVariable int money) {
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

    @GetMapping({"/money/{name1}/{money}/{name2}"})
    public ResponseEntity<Response> moneyToGamerFromGamerName(
            @PathVariable String name1,
            @PathVariable int money,
            @PathVariable String name2
    ) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message(name1 + " money " + money + " send to " + name2)
                        .data(of("gamer", gamerService.moneyToGamerFromGamerName(name1, money, name2)))
                        .build()
        );
    }
}
