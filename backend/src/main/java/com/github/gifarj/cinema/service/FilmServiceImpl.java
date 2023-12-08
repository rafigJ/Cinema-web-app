package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.FilmDto;
import com.github.gifarj.cinema.dto.FullFilmDto;
import com.github.gifarj.cinema.dto.GenreDto;
import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.entity.GenreEntity;
import com.github.gifarj.cinema.exception.NotFoundException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.FilmRepository;
import com.github.gifarj.cinema.repository.GenreRepository;
import com.github.gifarj.cinema.utils.DateTimeUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<FilmDto> searchByName(String name, int offset, int limit) {
        var searchFilms = repository.findAllByNameContainsIgnoreCase(name, PageRequest.of(offset, limit));
        if (searchFilms.isEmpty()) {
            throw new NotFoundException("Film by name: " + name + " not Found");
        }
        return searchFilms.map(film -> modelMapper.map(film, FilmDto.class));
    }

    @Override
    public Page<FilmDto> getFilmsPage(int offset, int limit) {
        var films = repository.findAll(PageRequest.of(offset, limit, Sort.by("id"))); // TODO: не сортировать
        return films.map(film -> modelMapper.map(film, FilmDto.class));
    }

    @Override
    public FullFilmDto getFilmById(Integer id) {
        try {
            var filmEntity = repository.getReferenceById(id);
            return modelMapper.map(filmEntity, FullFilmDto.class);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Film by id: " + id + " not Found");
        }
    }

    @Override
    public FullFilmDto createFilm(FullFilmDto film) {
        FilmEntity entity = convertFullFilmDtoToEntity(film, genreRepository);
        try {
            FilmEntity createdFilm = repository.saveAndFlush(entity);
            return modelMapper.map(createdFilm, FullFilmDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new RestException("Client error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FullFilmDto updateFilm(Integer id, FullFilmDto film) {
        var optionalFilmEntity = repository.findById(id);
        if (optionalFilmEntity.isEmpty()) {
            throw new NotFoundException("Film by id: " + id + " not found");
        }

        var filmEntity = convertFullFilmDtoToEntity(film, genreRepository);
        filmEntity.setId(id);
        repository.save(filmEntity);

        return modelMapper.map(filmEntity, FullFilmDto.class);
    }

    @Override
    public void deleteFilm(Integer id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new RestException("Client error: id must be not null", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SessionDto> getFilmSessionsByPeriod(Integer filmId, LocalDate start, LocalDate end) {
        try {
            var filmEntity = repository.getReferenceById(filmId);

            return filmEntity.getSessions().stream()
                    .filter(session -> DateTimeUtil.isDateBetweenInclusive(session.getDate(), start, end))
                    .map(session -> modelMapper.map(session, SessionDto.class))
                    .toList();
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Film by id: " + filmId + " not Found");
        }
    }

    /**
     * Converts a {@link FullFilmDto} object to a {@link FilmEntity} object, including genre validation.
     *
     * @param filmDto           The {@link FullFilmDto} object to be converted.
     * @param genreRepository   The {@link GenreRepository} used for genre validation.
     * @return                  A {@link FilmEntity} representing the converted film.
     * @throws NotFoundException If a GenreDto with a specified ID or name is not found in the genreRepository.
     * @throws RestException     If neither ID nor name is present in a GenreDto, or if the ID and name do not match.
     */
    private static FilmEntity convertFullFilmDtoToEntity(FullFilmDto filmDto, GenreRepository genreRepository) {
        var dtoGenres = filmDto.getGenres();
        validateGenreRequestDto(dtoGenres, genreRepository);
        var entityGenres = dtoGenres.stream().map(dto -> {
            if (dto.getId() != null) {
                return genreRepository.findById(dto.getId()).orElseThrow();
            } else if (filmDto.getName() != null) {
                return genreRepository.findByNameIgnoreCase(filmDto.getName()).orElseThrow();
            } else {
                throw new RestException("Server error ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }).toList();
        var filmEntity = new FilmEntity();
        filmEntity.setName(filmDto.getName());
        filmEntity.setYear(filmDto.getYear());
        filmEntity.setPoster(filmDto.getPoster());
        filmEntity.setDescription(filmDto.getDescription());
        filmEntity.setGenres(entityGenres);
        return filmEntity;
    }

    /**
     * Validates a list of GenreDto objects against the data stored in the GenreRepository.
     * The validation includes checking if the provided GenreDto objects have valid IDs or names
     * and verifying if the data matches the records in the genreRepository.
     *
     * @param genres      A list of GenreDto objects to be validated.
     * @param genreRepository  The GenreRepository used for data validation.
     * @throws NotFoundException    If a GenreDto with a specified ID or name is not found in the genreRepository.
     * @throws RestException        If neither ID nor name is present in a GenreDto, or if the ID and name do not match.
     */
    private static void validateGenreRequestDto(List<GenreDto> genres, GenreRepository genreRepository) {
        for (GenreDto dto : genres) {
            Optional<GenreEntity> optionalGenre;
            if (dto.getId() != null) {
                optionalGenre = genreRepository.findById(dto.getId());
                if (optionalGenre.isEmpty()) {
                    throw new NotFoundException("Genre by id: " + dto.getId() + " not Found");
                }
            } else if (dto.getName() != null) {
                optionalGenre = genreRepository.findByNameIgnoreCase(dto.getName());
                if (optionalGenre.isEmpty()) {
                    throw new NotFoundException("Genre by name: " + dto.getName() + " not Found");
                }
            } else {
                throw new RestException("Genre id or name must be present", HttpStatus.BAD_REQUEST);
            }

            var correctGenre = optionalGenre.get();
            if (dto.getId() != null && dto.getName() != null
                    && !correctGenre.getName().equalsIgnoreCase(dto.getName())) {
                throw new RestException("Genre by id " + dto.getId()
                        + " does not match the dto name " + dto.getName()
                        + ". Remove the id/name in the name or change it", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
