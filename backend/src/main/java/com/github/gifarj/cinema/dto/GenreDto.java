package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    // todo поменять макс
    @NotNull(message = "genre id should be not empty")
    @Min(1)
    @Max(57)
    private Integer id;
    private String name;
}
