package ru.vsu.cs.dzhabbarov.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.dzhabbarov.cinema.entity.GenreEntity;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

}
