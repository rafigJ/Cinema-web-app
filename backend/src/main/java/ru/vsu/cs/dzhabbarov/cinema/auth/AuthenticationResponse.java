package ru.vsu.cs.dzhabbarov.cinema.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.dzhabbarov.cinema.entity.UserEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String name;
    private String email;
    private String role;
    private String token;

    public static AuthenticationResponse userToResponse(UserEntity userEntity, String token) {
        return AuthenticationResponse.builder()
                .name(userEntity.getFirstname() + ' ' + userEntity.getLastname())
                .email(userEntity.getEmail())
                .role(userEntity.getRole().name())
                .token(token)
                .build();
    }
}
