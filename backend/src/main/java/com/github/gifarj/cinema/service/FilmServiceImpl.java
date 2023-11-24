package ru.vsu.cs.dzhabbarov.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dzhabbarov.cinema.dto.FilmDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.FullFilmDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.GenreDto;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.entity.GenreEntity;
import ru.vsu.cs.dzhabbarov.cinema.exception.RestException;
import ru.vsu.cs.dzhabbarov.cinema.repository.FilmRepository;
import ru.vsu.cs.dzhabbarov.cinema.repository.GenreRepository;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;
    private final GenreRepository genreRepository;

    @Override
    public Page<FilmDto> searchFilmByName(String name, int offset, int limit) {
        var searchFilms = repository.findAllByNameContainsIgnoreCase(name, PageRequest.of(offset, limit));
        if (searchFilms.isEmpty()) {
            throw new RestException("Film by name: " + name + " not Found", HttpStatus.NOT_FOUND);
        }
        return searchFilms.map(entityToDtoConverter());
    }

    @Override
    public Page<FilmDto> getPaginationFilms(int offset, int limit) {
        var films = repository.findAll(PageRequest.of(offset, limit, Sort.by("id")));
        return films.map(entityToDtoConverter());
    }

    @Override
    public FullFilmDto getFilmById(Integer id) {
        var filmEntity = repository.getReferenceById(id);
        return convertEntityToFullDto(filmEntity);
    }

    @Override
    public FullFilmDto createFilm(FullFilmDto film) {
        FilmEntity entity = convertFullDtoToEntity(film, genreRepository);
        if (entity == null) {
            throw new RestException("Entity is null", HttpStatus.BAD_REQUEST);
        }
        try {
            FilmEntity createdFilm = repository.saveAndFlush(entity);
            film.setId(createdFilm.getId());
            return film;
        } catch (DataIntegrityViolationException e) {
            throw new RestException("Client error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateFilm(Integer id, FullFilmDto film) {
        var optionalFilmEntity = repository.findById(id);
        if (optionalFilmEntity.isEmpty()) {
            throw new RestException("Client error: film with id:" + id + " field not exist", HttpStatus.NOT_FOUND);
        }

        var filmEntity = optionalFilmEntity.get();
        filmEntity.setName(film.getName());
        filmEntity.setYear(film.getYear());
        filmEntity.setDescription(film.getDescription());
        filmEntity.setPosterUrl(filmEntity.getPosterUrl());

        var genreIds = film.getGenres().stream()
                .map(GenreDto::getId)
                .toList();
        List<GenreEntity> genres = genreRepository.findAllById(genreIds);
        filmEntity.setGenres(genres);
        repository.save(filmEntity);
    }

    @Override
    public void deleteFilm(Integer id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new RestException("Client error: id must be not null", HttpStatus.BAD_REQUEST);
        }
    }

    private static Function<FilmEntity, FilmDto> entityToDtoConverter() {
        return e -> {
            if (e == null) {
                return null;
            }
            var genres = e.getGenres().stream()
                    .map(g -> new GenreDto(g.getId(), g.getName()))
                    .toList();
            return FilmDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .year(e.getYear())
                    .poster(e.getPosterUrl())
                    .genres(genres)
                    .build();
        };
    }

    private static FullFilmDto convertEntityToFullDto(FilmEntity e) {
        if (e == null) {
            return null;
        }
        var genres = e.getGenres()
                .stream()
                .map(g -> new GenreDto(g.getId(), g.getName()))
                .toList();

        return FullFilmDto.builder()
                .id(e.getId())
                .name(e.getName())
                .year(e.getYear())
                .poster(e.getPosterUrl())
                .description(e.getDescription())
                .genres(genres)
                .build();
    }

    private static FilmEntity convertFullDtoToEntity(FullFilmDto film, GenreRepository genreRepository) {
        if (film == null) {
            return null;
        }
        var entity = new FilmEntity();
        var genreIds = film.getGenres().stream()
                .map(GenreDto::getId)
                .toList();
        List<GenreEntity> genres = genreRepository.findAllById(genreIds);
        entity.setName(film.getName());
        entity.setPosterUrl(film.getPoster());
        entity.setDescription(film.getDescription());
        entity.setYear(film.getYear());
        entity.setGenres(genres);
        return entity;
    }
}
