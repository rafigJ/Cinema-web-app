package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.entity.SessionEntity;
import com.github.gifarj.cinema.exception.NotFoundException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.FilmRepository;
import com.github.gifarj.cinema.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<SessionDto> getSessions(Pageable pageable, LocalDate start, LocalDate end) {
        return repository.findAllByDateBetween(start, end, pageable).map(e -> modelMapper.map(e, SessionDto.class));
    }

    @Override
    public SessionDto getSessionById(Integer id) {
        SessionEntity session = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Session by id: " + id + " not Found")
        );
        return modelMapper.map(session, SessionDto.class);
    }

    @Override
    public SessionDto createSession(SessionDto session) {
        SessionEntity sessionEntity = convertToEntity(session);
        try {
            repository.saveAndFlush(sessionEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RestException("Client error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return convertToDto(sessionEntity);
    }

    @Override
    public SessionDto updateSession(Integer id, SessionDto session) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Session by id: " + id + " not found");
        }
        SessionEntity sessionEntity = convertToEntity(session);
        session.setId(id);
        try {
            repository.saveAndFlush(sessionEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RestException("Client error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return convertToDto(sessionEntity);
    }

    @Override
    public void deleteSession(Integer id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new RestException("Client error: id must be not null", HttpStatus.BAD_REQUEST);
        }
    }

    private SessionEntity convertToEntity(SessionDto dto) {
        return modelMapper.map(dto, SessionEntity.class);
    }

    private SessionDto convertToDto(SessionEntity entity) {
        SessionDto dto = modelMapper.map(entity, SessionDto.class);
        FilmEntity film = filmRepository.findById(dto.getFilmId()).orElseThrow(() ->
                new NotFoundException("Film by id: " + dto.getFilmId() + " not found")
        );
        dto.setFilmName(film.getName());
        return dto;
    }
}
