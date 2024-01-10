package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.HallEntity;
import com.github.gifarj.cinema.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HallRepository extends JpaRepository<HallEntity, Integer> {

}
