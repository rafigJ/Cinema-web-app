package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.dto.film.FilmDto;
import com.github.gifarj.cinema.dto.film.FullFilmDto;
import com.github.gifarj.cinema.criteria.FilmCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {

    Page<FilmDto> getFilms(Pageable pageable);

    Page<FilmDto> filterFilms(FilmCriteria criteria, Pageable pageable);

    FullFilmDto getFilmById(Integer id);

    FullFilmDto createFilm(FullFilmDto film);

    FullFilmDto updateFilm(Integer id, FullFilmDto film);

    void deleteFilm(Integer id);

    List<SessionDto> getFilmSessionsByPeriod(Integer filmId, LocalDate start, LocalDate end);
}
