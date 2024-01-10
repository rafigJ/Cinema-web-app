package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.criteria.FilmCriteria;
import com.github.gifarj.cinema.dto.GenreDto;
import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.dto.film.FilmDto;
import com.github.gifarj.cinema.dto.film.FullFilmDto;
import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.entity.GenreEntity;
import com.github.gifarj.cinema.entity.SessionEntity;
import com.github.gifarj.cinema.exception.NotFoundException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.FilmRepository;
import com.github.gifarj.cinema.repository.GenreRepository;
import com.github.gifarj.cinema.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;
    private final GenreRepository genreRepository;
    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<FilmDto> getFilms(Pageable pageable) {
        Page<FilmEntity> films = repository.findAll(pageable);
        return films.map(film -> modelMapper.map(film, FilmDto.class));
    }

    @Override
    public Page<FilmDto> filterFilms(FilmCriteria criteria, Pageable pageable) {
        Page<FilmEntity> films = repository.findAll(criteria.getSpecification(), pageable);
        return films.map(film -> modelMapper.map(film, FilmDto.class));
    }

    @Override
    public FullFilmDto getFilmById(Integer id) {
        FilmEntity film = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Film by id: " + id + " not Found")
        );
        return modelMapper.map(film, FullFilmDto.class);
    }

    @Override
    public FullFilmDto createFilm(FullFilmDto film) {
        FilmEntity entity = convertFullFilmDtoToEntity(film);
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
        if (!repository.existsById(id)) {
            throw new NotFoundException("Film by id: " + id + " not found");
        }
        FilmEntity filmEntity = convertFullFilmDtoToEntity(film);
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
        List<SessionEntity> sessionsList = sessionRepository.findAllByFilmIdAndDateBetween(filmId, start, end);
        return sessionsList.stream().map(e -> modelMapper.map(e, SessionDto.class)).toList();
    }

    /**
     * Converts a FullFilmDto object to a FilmEntity object.
     *
     * @param dto The FullFilmDto object to be converted.
     * @return FilmEntity object representing the converted FullFilmDto.
     * @throws RestException if genre id or name is missing or if there is a conflict between
     *                       the provided genre id and name.
     * @throws NotFoundException if the genre is not found in the database by id or name.
     */
    private FilmEntity convertFullFilmDtoToEntity(FullFilmDto dto) {
        List<GenreDto> dtoGenres = dto.getGenres();

        List<GenreEntity> entityGenres = dtoGenres.stream().map(this::convertGenreDtoToEntity).toList();

        return FilmEntity.builder()
                .name(dto.getName())
                .year(dto.getYear())
                .description(dto.getDescription())
                .poster(dto.getPoster())
                .genres(entityGenres)
                .build();
    }

    private GenreEntity convertGenreDtoToEntity(GenreDto dto) {
        Integer gId = dto.getId();
        String gName = dto.getName();

        if (gId == null && StringUtils.isEmpty(gName)) {
            throw new RestException("Genre id or name must be present", HttpStatus.BAD_REQUEST);
        }

        GenreEntity res;
        if (gId != null) {
            res = genreRepository.findById(gId).orElseThrow(() ->
                    new NotFoundException("Genre by id: " + gId + " not Found"));

            if (StringUtils.isNotEmpty(gName) && !gName.equalsIgnoreCase(res.getName())) {
                // название жанра в запросе не соответствует названию из БД (при наличии id в запросе)
                throw new RestException("Genre by id " + gId +
                        " does not match the dto name " + gName +
                        ". Remove the id/name in the name or change it",
                        HttpStatus.BAD_REQUEST
                );
            }
        } else {
            res = genreRepository.findByNameIgnoreCase(gName).orElseThrow(() ->
                    new NotFoundException("Genre by name: " + gName + " not Found"));
        }

        return res;
    }
}
