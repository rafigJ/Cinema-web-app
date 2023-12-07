package com.github.gifarj.cinema.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
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
