package ru.vsu.cs.dzhabbarov.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.repository.FilmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;

    public List<FilmEntity> findFilmByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }

    public Page<FilmEntity> getAllFilms(int offset, int limit) {
        return repository.findAll(PageRequest.of(offset, limit, Sort.by("id")));
    }

}
