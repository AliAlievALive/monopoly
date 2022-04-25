package ru.halal.monopoly.controller;

import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.TypeWrapper;
import ru.halal.monopoly.service.GamerService;

import java.util.Collection;

@RestController
@RequestMapping("/api/gamers")
public record GamerController(GamerService gamerService) {

    @GetMapping
    public Collection<Gamer> getGamers() {
        return gamerService.getGamers();
    }

    @GetMapping({"{id}"})
    public Gamer getGamer(@PathVariable int id) {
        return gamerService.getGamerById(id);
    }

    @PostMapping
    public Gamer saveGamer(@RequestBody Gamer gamer) {
        return gamerService.create(gamer);
    }

    @PutMapping
    public Gamer updateGamer(@RequestBody Gamer gamer) {
        return gamerService.update(gamer);
    }

    @DeleteMapping("{id}")
    public Boolean deleteGamer(@PathVariable int id) {
        return gamerService.delete(id);
    }

    @PostMapping({"/add_own_to_gamer/{ownId}/{gamerId}"})
    public Boolean addOwnToGamer(@PathVariable int ownId,
                                 @PathVariable int gamerId,
                                 @RequestBody TypeWrapper type) {
        return gamerService.addOwnToGamer(ownId, gamerId, type);
    }

    @PostMapping({"/give_to_another_gamer/{fromId}/{toId}/{ownId}"})
    public Boolean ownToAnotherGamer(@RequestBody TypeWrapper type, @PathVariable int fromId, @PathVariable int toId, @PathVariable int ownId) {
        return gamerService.giveOwnToAnotherGamer(type, fromId, toId, ownId);
    }

    @GetMapping({"/money/{id}/{money}"})
    public int moneyToGamer(@PathVariable int id, @PathVariable int money) {
        return gamerService.changeMoneyCounts(id, money);
    }

    @GetMapping({"/money/{name1}/{money}/{name2}"})
    public Boolean moneyToGamerFromGamerName(
            @PathVariable String name1,
            @PathVariable int money,
            @PathVariable String name2
    ) {
        return gamerService.moneyToGamerFromGamerName(name1, money, name2);
    }
}
