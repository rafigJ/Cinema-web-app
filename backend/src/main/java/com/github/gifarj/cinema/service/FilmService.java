package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.FilmDto;
import com.github.gifarj.cinema.dto.FullFilmDto;
import org.springframework.data.domain.Page;

public interface FilmService {

    Page<FilmDto> searchFilmByName(String name, int offset, int limit);

    Page<FilmDto> getPaginationFilms(int offset, int limit);

    FullFilmDto getFilmById(Integer id);

    FullFilmDto createFilm(FullFilmDto film);

    FullFilmDto updateFilm(Integer id, FullFilmDto film);

    void deleteFilm(Integer id);
}
