package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer>, JpaSpecificationExecutor<FilmEntity> {

}
