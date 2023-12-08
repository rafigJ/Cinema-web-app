package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HallDto {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    @Min(2)
    private Short rowCount;

    @NotNull
    @Min(2)
    private Short columnCount;
}
