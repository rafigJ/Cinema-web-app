package com.github.gifarj.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.gifarj.cinema.dto.film.FilmDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
 * ВАЖНО: Поля помеченные @NotNull используется и при создании сеанса.
 */
@Schema(name = "Session")
public class SessionDto {

    @Null
    private Integer id;

    @NotNull
    private Integer filmId;

    @NotNull
    private Integer hallId;

    @Null
    private FilmDto film;

    @Null
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
