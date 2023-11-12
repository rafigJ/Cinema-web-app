package ru.vsu.cs.dzhabbarov.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.repository.FilmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository repository;

    public List<FilmEntity> getAllFilms() {
        return repository.findAll();
    }

    public List<FilmEntity> findFilmsByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }
}
