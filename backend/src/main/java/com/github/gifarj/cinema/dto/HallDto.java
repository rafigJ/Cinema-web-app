package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HallDto {

    @Null
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 200)
    private String name;

    @NotNull
    @Min(3)
    private Short rowCount;

    @NotNull
    @Min(3)
    private Short columnCount;
}
