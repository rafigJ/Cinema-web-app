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
public class FullFilmDto {
    private int id;
    private String name;
    private int year;
    private String poster;
    private String description;
    private List<String> genres;
}
