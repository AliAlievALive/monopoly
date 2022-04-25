package ru.halal.monopoly.controller;

import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.service.CommunalService;

import java.util.Collection;

@RestController
@RequestMapping("/api/ownership/communal")
public record CommunalController(CommunalService communalService) {
    @GetMapping
    public Collection<Communal> getCommunalList() {
        return communalService.getCommunalList(2);
    }

    @GetMapping({"{id}"})
    public Communal getCommunal(@PathVariable int id) {
        return communalService.getCommunal(id);
    }

    @PostMapping
    public Communal saveCommunal(@RequestBody Communal communal) {
        return communalService.createOrUpdate(communal);
    }

    @PutMapping
    public Communal updateCommunal(@RequestBody Communal communal) {
        return communalService.update(communal);
    }

    @DeleteMapping("{id}")
    public Boolean deleteCommunal(@PathVariable int id) {
        return communalService.delete(id);
    }
}
