package ru.vsu.cs.dzhabbarov.cinema.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestExceptionResponse {
    private String message;
}
