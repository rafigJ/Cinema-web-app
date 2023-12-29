package com.github.gifarj.cinema.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    @NotEmpty(message = "should be not empty")
    private String email;

    @NotEmpty(message = "should be not empty")
    private String password;
}
