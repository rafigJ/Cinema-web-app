package ru.vsu.cs.dzhabbarov.cinema.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/film-controller")
public class FilmController {

    @GetMapping
    public ResponseEntity<String> filmHello() {
        return ResponseEntity.ok("Ура, получилось?");
    }

}
