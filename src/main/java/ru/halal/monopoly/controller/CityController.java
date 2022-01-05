package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halal.monopoly.domain.Response;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.service.CityService;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/ownership/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/list")
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

    @GetMapping({"/{id}"})
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

    @PostMapping("/save")
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

    @PutMapping("/update")
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

    @DeleteMapping ("/delete/{id}")
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
}
