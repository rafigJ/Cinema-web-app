package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.SessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface SessionService {

    Page<SessionDto> getSessions(Pageable pageable, LocalDate start, LocalDate end);

    SessionDto getSessionById(Integer id);

    SessionDto createSession(SessionDto session);

    SessionDto updateSession(Integer id, SessionDto session);

    void deleteSession(Integer id);

}
