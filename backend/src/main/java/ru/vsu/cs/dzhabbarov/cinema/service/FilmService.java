package ru.vsu.cs.dzhabbarov.cinema.service;

import org.springframework.data.domain.Page;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;

import java.util.List;

public interface FilmService {

    List<FilmEntity> findFilmByName(String name);

    Page<FilmEntity> getAllFilms(int offset, int limit);
}
