package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    Optional<GenreEntity> findByNameIgnoreCase(String name);
}
