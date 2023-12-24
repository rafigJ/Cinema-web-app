package com.github.gifarj.cinema.dto.film;

import com.github.gifarj.cinema.dto.GenreDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullFilmDto {
    private Integer id;

    @NotEmpty(message = "film name should be not empty")
    @Size(min = 2, max = 250, message = "film name should contain at least 2 characters and no more than 250 characters")
    private String name;

    @Min(value = 1800, message = "film year should be more than 1800")
    private Integer year;

    @NotEmpty(message = "film poster should be not empty")
    @URL(message = "film poster should contain only poster URL")
    private String poster;

    @NotEmpty(message = "film description should be not empty")
    private String description;

    @NotEmpty(message = "film genres should be not empty")
    private List<@Valid GenreDto> genres;
}
