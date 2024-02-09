package com.github.gifarj.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Genre")
public class GenreDto {
    @Min(1)
    @Max(57)
    private Integer id;
    private String name;
}
