package com.github.gifarj.cinema.dto.user;

import com.github.gifarj.cinema.user.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUserDto {

    @NotNull
    private UUID uuid;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Role role;

    @NotNull
    private Integer money;

    @NotNull
    private LocalDateTime createTime;

    @NotNull
    private LocalDateTime updateTime;
   
}