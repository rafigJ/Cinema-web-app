package ru.vsu.cs.dzhabbarov.cinema.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.dzhabbarov.cinema.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String name;
    private String email;
    private String role;
    private String token;

    public static AuthenticationResponse userToResponse(User user, String token) {
        return AuthenticationResponse.builder()
                .name(user.getFirstname() + ' ' + user.getLastname())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .build();
    }
}
