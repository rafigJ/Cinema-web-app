package com.github.gifarj.cinema.dto;

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
    private int id;
    private String name;
    private int year;
    private String poster;
    private List<String> genres;
}
