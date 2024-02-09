package com.github.gifarj.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.gifarj.cinema.dto.views.TicketView;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Ticket")
public class TicketDto {

    @Null
    private Integer id;

    @Null
    private UUID userUuid;

    @JsonView(TicketView.Occupied.class)
    @NotNull(message = "should be not null")
    private Integer sessionId;

    @JsonView(TicketView.Occupied.class)
    @NotNull(message = "row must be not null")
    @Min(value = 1, message = "must be greater than 1")
    private Short row;

    @JsonView(TicketView.Occupied.class)
    @NotNull(message = "column must be not null")
    @Min(value = 1, message = "the number must be greater than 1")
    private Short column;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime buyTime;
}