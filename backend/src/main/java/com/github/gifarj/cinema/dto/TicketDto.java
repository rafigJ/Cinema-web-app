package com.github.gifarj.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    @Null
    private Integer id;

    @Null
    private UUID userUuid;

    @NotNull(message = "should be not null")
    private Integer sessionId;

    @NotNull(message = "row must be not null")
    @Min(value = 1, message = "must be greater than 1")
    private Short row;

    @NotNull(message = "column must be not null")
    @Min(value = 1, message = "the number must be greater than 1")
    private Short column;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime buyTime;
}