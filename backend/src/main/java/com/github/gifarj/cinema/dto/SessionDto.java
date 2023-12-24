package com.github.gifarj.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    private Integer id;

    @NotNull
    private Integer filmId;

    private String filmName;

    @NotNull
    private HallDto hall;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotNull
    @Min(1)
    private Integer price;
}
