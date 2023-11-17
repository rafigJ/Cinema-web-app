package ru.vsu.cs.dzhabbarov.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestExceptionDto {
    private String message;
}
