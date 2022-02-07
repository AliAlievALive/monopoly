package ru.halal.monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.service.CityService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership/cities")
public record CityController(CityService cityService) {
    @PostMapping
    public ResponseEntity<Response> saveCity(@RequestBody City city) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("City " + city.getName() + " is save")
                        .data(of("city", cityService.create(city)))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<Response> getCityList() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Cities list limited 10")
                        .data(of("city", cityService.getCities(10)))
                        .build()
        );
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Response> getCity(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("City with " + id + " is found")
                        .data(of("city", cityService.getCity(id)))
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateCity(@RequestBody City city) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("City with id " + city.getId() + " is updated")
                        .data(of("city", cityService.update(city)))
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteCity(@PathVariable int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("City with id " + id + " is deleted")
                        .data(of("city", cityService.delete(id)))
                        .build()
        );
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
