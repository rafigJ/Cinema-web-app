package ru.vsu.cs.dzhabbarov.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {
    private Integer id;
    private String name;
    private Integer year;
    private String poster;
    private List<GenreDto> genres;
}
