package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.exception_handling.GamerIncorrectData;
import ru.halal.monopoly.exception_handling.NoSuchGamerException;
import ru.halal.monopoly.service.GamerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gamer")
@RequiredArgsConstructor
public class GamerController {
    private final GamerService gamerService;

    @GetMapping("/list")
    public List<Gamer> getGamers() {
        return gamerService.getGamers();
    }

//    @GetMapping({"/{id}"})
//    public ResponseEntity<Gamer> getGamer(@PathVariable int id) {
//        Optional<Gamer> optionalGamer = gamerService.getGamer(id);
//        if (optionalGamer.isPresent()) {
//            Gamer gamer = optionalGamer.get();
//            return ResponseEntity.ok(
//                    Gamer.builder()
//                            .id(gamer.getId())
//                            .name(gamer.getName())
//                            .money(gamer.getMoney())
//                            .cities(gamer.getCities())
//                            .communal(gamer.getCommunal())
//                            .airports(gamer.getAirports())
//                            .build()
//            );
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping({"/{id}"})
    public Gamer getGamer(@PathVariable int id) {
        Gamer gamer = gamerService.getGamer(id);

        if (gamer == null) {
            throw new NoSuchGamerException("There is no Gamer with id = " + id + " in Database");
        }

        return gamer;
    }

    @ExceptionHandler
    public ResponseEntity<GamerIncorrectData> handleException(NoSuchGamerException exception) {
        GamerIncorrectData data = new GamerIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
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
