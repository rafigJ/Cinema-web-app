package com.github.gifarj.cinema.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.gifarj.cinema.dto.views.UserView;
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
public class UserDto {

    @NotNull
    @JsonView(UserView.NotPrivate.class)
    private UUID uuid;

    @NotNull
    @JsonView(UserView.NotPrivate.class)
    private String name;

    @NotNull
    @JsonView(UserView.NotPrivate.class)
    private String email;

    @NotNull
    @JsonView(UserView.NotPrivate.class)
    private Integer money;

    @NotNull
    private Role role;

    @NotNull
    private LocalDateTime createTime;

    @NotNull
    private LocalDateTime updateTime;
}