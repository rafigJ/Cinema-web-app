package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

}
