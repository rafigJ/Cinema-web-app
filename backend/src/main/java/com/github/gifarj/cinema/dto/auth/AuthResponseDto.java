package com.github.gifarj.cinema.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Auth Response")
public class AuthResponseDto {
    private String name;
    private String email;
    private String role;
    private String token;
}
