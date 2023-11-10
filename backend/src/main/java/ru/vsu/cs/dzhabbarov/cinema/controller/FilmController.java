package ru.vsu.cs.dzhabbarov.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.repository.FilmRepository;
import ru.vsu.cs.dzhabbarov.cinema.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/film-controller")
public class FilmController {

    private final FilmRepository repository;
    @GetMapping
    public ResponseEntity<List<FilmEntity>> filmHello(@AuthenticationPrincipal User principal) {
        return ResponseEntity.ok(repository.findAll());
    }

}
