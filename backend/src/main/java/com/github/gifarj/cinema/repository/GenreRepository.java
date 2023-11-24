package com.github.gifarj.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.gifarj.cinema.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

}
