package com.github.gifarj.cinema.dto.film;

import com.github.gifarj.cinema.dto.GenreDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Film")
public class FilmDto {
    private Integer id;
    private String name;
    private Integer year;
    private String poster;
    private List<GenreDto> genres;
}
