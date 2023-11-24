package ru.vsu.cs.dzhabbarov.cinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dzhabbarov.cinema.dto.FilmDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.FullFilmDto;
import ru.vsu.cs.dzhabbarov.cinema.service.FilmService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    private FullFilmDto getFilmById(@PathVariable("id") Integer id) {
        return service.getFilmById(id);
    }

    @GetMapping()
    public Page<FilmDto> getFilmsPagination(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return service.getPaginationFilms(offset, limit);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<FilmDto> searchFilms(@RequestParam("query") String query,
                                     @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return service.searchFilmByName(query, offset, limit);
    }

    @PostMapping()
    public ResponseEntity<FilmDto> createFilm(@RequestBody @Valid FullFilmDto filmDto) {
        var film = service.createFilm(filmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }
}
