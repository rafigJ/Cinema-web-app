package ru.vsu.cs.dzhabbarov.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.dzhabbarov.cinema.entity.FilmEntity;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
    List<FilmEntity> findAllByNameContainingIgnoreCase(String name);

    FilmEntity getFirstByNameContaining(String name);
}
