package com.github.gifarj.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.gifarj.cinema.entity.GenreEntity;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    List<GenreEntity> findByNameIn(Collection<String> strings);
}
