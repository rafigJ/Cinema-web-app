package ru.vsu.cs.dzhabbarov.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
