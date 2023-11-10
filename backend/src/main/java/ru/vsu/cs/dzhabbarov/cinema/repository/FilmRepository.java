package ru.vsu.cs.dzhabbarov.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
    List<FilmEntity> findAllByNameContainingIgnoreCase(String name);
}
