package ru.vsu.cs.dzhabbarov.cinema.service;

import org.springframework.data.domain.Page;
import ru.vsu.cs.dzhabbarov.cinema.dto.FilmDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.FullFilmDto;

public interface FilmService {

    Page<FilmDto> searchFilmByName(String name, int offset, int limit);

    Page<FilmDto> getPaginationFilms(int offset, int limit);

    FullFilmDto getFilmById(int id);

    FilmDto createFilm(FullFilmDto film);

    void updateFilm(FullFilmDto film);

    void deleteFilm(int id);
}
