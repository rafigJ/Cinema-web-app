package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.FilmDto;
import com.github.gifarj.cinema.dto.FullFilmDto;
import com.github.gifarj.cinema.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService service;

    @GetMapping("/{id}")
    private FullFilmDto getById(@PathVariable("id") Integer id) {
        return service.getFilmById(id);
    }

    @GetMapping()
    public Page<FilmDto> getFilmsPagination(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return service.getFilmsPage(page, limit);
    }

    @GetMapping("/search")
    public Page<FilmDto> searchFilms(@RequestParam("name") String name,
                                     @RequestParam(value = "_page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return service.searchByName(name, page, limit);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FullFilmDto createFilm(@Valid @RequestBody FullFilmDto filmDto) {
        return service.createFilm(filmDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FullFilmDto updateFilm(@NotNull @PathVariable Integer id,
                                  @Valid @RequestBody FullFilmDto filmDto) {
        return service.updateFilm(id, filmDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@NotNull @PathVariable Integer id) {
        service.deleteFilm(id);
    }

}
