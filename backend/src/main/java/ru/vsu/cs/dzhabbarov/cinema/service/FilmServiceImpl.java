package ru.vsu.cs.dzhabbarov.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dzhabbarov.cinema.dto.FilmDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.FullFilmDto;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;
import ru.vsu.cs.dzhabbarov.cinema.entity.GenreEntity;
import ru.vsu.cs.dzhabbarov.cinema.exception.RestException;
import ru.vsu.cs.dzhabbarov.cinema.repository.FilmRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;

    @Override
    public Page<FilmDto> searchFilmByName(String name, int offset, int limit) {
        var searchFilms = repository.findAllByNameContainsIgnoreCase(name, PageRequest.of(offset, limit));
        if (searchFilms.isEmpty()) {
            throw new RestException("Films by name: " + name + " Not Found", HttpStatus.NOT_FOUND);
        }
        return searchFilms.map(entityToDtoConverter());
    }

    @Override
    public Page<FilmDto> getPaginationFilms(int offset, int limit) {
        var films = repository.findAll(PageRequest.of(offset, limit, Sort.by("id")));
        return films.map(entityToDtoConverter());
    }

    @Override
    public FullFilmDto getFilmById(int id) {
        var filmEntity = repository.getReferenceById(id);
        return convertEntityToFullDto(filmEntity);
    }

    private static Function<FilmEntity, FilmDto> entityToDtoConverter() {
        return e -> {
            if (e == null) {
                return null;
            }
            var genres = e.getGenres().stream().map(GenreEntity::getName).toList();
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
        return FullFilmDto.builder()
                .id(e.getId())
                .name(e.getName())
                .year(e.getYear())
                .poster(e.getPosterUrl())
                .description(e.getDescription())
                .build();
    }
}
