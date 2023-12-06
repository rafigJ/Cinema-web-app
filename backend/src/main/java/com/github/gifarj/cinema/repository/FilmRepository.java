package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.FilmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    Page<FilmEntity> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
