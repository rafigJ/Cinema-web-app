package com.github.gifarj.cinema.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Integer money;

}