package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.HallEntity;
import com.github.gifarj.cinema.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<HallEntity, Integer> {

}
