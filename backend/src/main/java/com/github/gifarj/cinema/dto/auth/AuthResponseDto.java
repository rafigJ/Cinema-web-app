package com.github.gifarj.cinema.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String name;
    private String email;
    private String role;
    private String token;
}
