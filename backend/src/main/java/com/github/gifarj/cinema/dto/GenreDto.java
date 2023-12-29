package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    @Min(1)
    @Max(57)
    private Integer id;
    private String name;
}
