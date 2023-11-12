package ru.vsu.cs.dzhabbarov.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.service.FilmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService service;

    @GetMapping
    public ResponseEntity<List<FilmEntity>> getFilms() {
        return ResponseEntity.ok(service.getAllFilms());
    }

}
