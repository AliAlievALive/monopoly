package ru.halal.monopoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.halal.monopoly.domain.Response;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class helloController {
    @GetMapping
    public ResponseEntity<Response> getHello() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message("Hello message")
                        .data(of("hello", "Hi my friend!"))
                        .build()
        );
    }
}
