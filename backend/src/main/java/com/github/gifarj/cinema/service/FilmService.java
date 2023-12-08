package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.FilmDto;
import com.github.gifarj.cinema.dto.FullFilmDto;
import com.github.gifarj.cinema.dto.SessionDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {

    Page<FilmDto> searchByName(String name, int offset, int limit);

    Page<FilmDto> getFilmsPage(int offset, int limit);

    FullFilmDto getFilmById(Integer id);

    FullFilmDto createFilm(FullFilmDto film);

    FullFilmDto updateFilm(Integer id, FullFilmDto film);

    void deleteFilm(Integer id);

    List<SessionDto> getFilmSessionsByPeriod(Integer filmId, LocalDate start, LocalDate end);
}
