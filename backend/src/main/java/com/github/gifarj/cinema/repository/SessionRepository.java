package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.SessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

    List<SessionEntity> findAllByFilmIdAndDateBetween(Integer filmId, LocalDate start, LocalDate end);

    Page<SessionEntity> findAllByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
