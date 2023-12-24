package com.github.gifarj.cinema.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Integer id;

    @NotNull
    private UUID userUuid;

    @NotNull
    private SessionDto session;

    @NotNull
    @Min(1)
    private Short row;

    @NotNull
    @Min(1)
    private Short column;

    private LocalDateTime buyTime;
}