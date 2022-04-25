package ru.halal.monopoly.controller;

import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.service.CityService;

import java.util.Collection;

@RestController
@RequestMapping("/api/ownership/cities")
public record CityController(CityService cityService) {
    @PostMapping
    public City saveCity(@RequestBody City city) {
        return cityService.create(city);
    }

    @GetMapping
    public Collection<City> getCityList() {
        return cityService.getCities(10);
    }

    @GetMapping({"{id}"})
    public City getCity(@PathVariable int id) {
        return cityService.getCity(id);
    }

    @PutMapping
    public City updateCity(@RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping("{id}")
    public Boolean deleteCity(@PathVariable int id) {
        return cityService.delete(id);
    }

//    @GetMapping({"/deposit/{id}"})
//    public ResponseEntity<Response> cityToDeposit(@PathVariable int id) {
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timestamp(now())
//                        .statusCode(OK.value())
//                        .status(OK)
//                        .message("City with id " + id + " set to deposit")
//                        .data(of("city", cityService.cityToDeposit(id)))
//                        .build()
//        );
//    }
//
//    @GetMapping({"/from_deposit/{id}"})
//    public ResponseEntity<Response> cityFromDeposit(@PathVariable int id) {
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timestamp(now())
//                        .statusCode(OK.value())
//                        .status(OK)
//                        .message("City with id " + id + " get from deposit")
//                        .data(of("city", cityService.cityFromDeposit(id)))
//                        .build()
//        );
//    }
//
//    @GetMapping({"/add_home/{id}"})
//    public ResponseEntity<Response> addHome(@PathVariable int id) {
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timestamp(now())
//                        .statusCode(OK.value())
//                        .status(OK)
//                        .message("To city with id " + id + " added home")
//                        .data(of("city", cityService.addHome(id)))
//                        .build()
//        );
//    }
//
//    @GetMapping({"/sold_home/{id}"})
//    public ResponseEntity<Response> soldHome(@PathVariable int id) {
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timestamp(now())
//                        .statusCode(OK.value())
//                        .status(OK)
//                        .message("From city with id " + id + " sold home")
//                        .data(of("city", cityService.soldHome(id)))
//                        .build()
//        );
//    }
}
