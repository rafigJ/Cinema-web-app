package ru.vsu.cs.dzhabbarov.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.service.FilmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService service;

    @GetMapping()
    public Page<FilmEntity> getFilmsPagination(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                               @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return service.getAllFilms(offset, limit);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmEntity>> getFilms(@RequestParam("query") String query) {
        return ResponseEntity.ok(service.findFilmByName(query));
    }

    @PostMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("защитаа");
    }
}
