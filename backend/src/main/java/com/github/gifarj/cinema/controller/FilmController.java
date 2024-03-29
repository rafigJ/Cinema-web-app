package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.criteria.FilmSort;
import com.github.gifarj.cinema.dto.PageDto;
import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.dto.film.FilmDto;
import com.github.gifarj.cinema.dto.film.FullFilmDto;
import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.exception.BadRequestException;
import com.github.gifarj.cinema.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static com.github.gifarj.cinema.criteria.FilmSpecifications.genresIdIn;
import static com.github.gifarj.cinema.criteria.FilmSpecifications.nameContainsIgnoreCase;

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
    public PageDto<FilmDto> getFilmsPage(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "_limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "genres", required = false) Collection<Integer> genreIds,
                                         @RequestParam(value = "sort", required = false) FilmSort sort) {
        Specification<FilmEntity> specifications = Specification.allOf(nameContainsIgnoreCase(name), genresIdIn(genreIds));

        if (sort == null) {
            Page<FilmDto> filmsPage = service.getFilms(specifications, PageRequest.of(page, limit));
            return PageDto.of(filmsPage);
        }

        Page<FilmDto> filmsPage = service.getFilms(specifications, PageRequest.of(page, limit, Sort.by(sort.getFieldName())));

        return PageDto.of(filmsPage);
    }

    @GetMapping("/{id}/sessions")
    public List<SessionDto> getSessionByPeriod(@PathVariable("id") Integer id,
                                               @RequestParam(value = "start", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                               @RequestParam(value = "end", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (end == null) {
            // by default end = start, or end = start = NOW
            end = (start == null) ? LocalDate.now() : start;
        }
        if (start == null) {
            start = LocalDate.now();
        }
        if (!start.equals(end) && start.isAfter(end)) {
            throw new BadRequestException("start must be before End");
        }
        return service.getFilmSessionsByPeriod(id, start, end);
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
