package com.github.gifarj.cinema.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Auth Request")
public class AuthRequestDto {
    @NotEmpty(message = "should be not empty")
    private String email;

    @NotEmpty(message = "should be not empty")
    private String password;
}
