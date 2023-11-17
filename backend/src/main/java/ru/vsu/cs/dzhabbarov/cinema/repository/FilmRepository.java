package ru.vsu.cs.dzhabbarov.cinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    Page<FilmEntity> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
