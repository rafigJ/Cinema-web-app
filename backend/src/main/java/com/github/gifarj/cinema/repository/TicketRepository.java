package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.TicketEntity;
import com.github.gifarj.cinema.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    Page<TicketEntity> findAllByOwner(UserEntity owner, Pageable pageable);

    List<TicketEntity> findAllBySessionId(Integer sessionId);

    List<TicketEntity> findAllByBuyTimeBetween(LocalDateTime start, LocalDateTime end);
}
