package ru.vsu.cs.dzhabbarov.cinema.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
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
    public ResponseEntity<Void> createFilm(@Valid @RequestBody FullFilmDto filmDto,
                                           UriComponentsBuilder uriBuilder) {
        var createdFilm = service.createFilm(filmDto);
        UriComponents uriComponents = uriBuilder.path("/api/v1/films/{id}").buildAndExpand(createdFilm.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFilm(@NotNull @PathVariable Integer id,
                                           @Valid @RequestBody FullFilmDto filmDto,
                                           UriComponentsBuilder uriBuilder) {
        service.updateFilm(id, filmDto);
        UriComponents uriComponents = uriBuilder.path("/api/v1/films/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@NotNull @PathVariable Integer id) {
        service.deleteFilm(id);
    }

}
