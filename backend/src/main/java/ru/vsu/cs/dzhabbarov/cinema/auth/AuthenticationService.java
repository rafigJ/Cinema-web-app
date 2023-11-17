package ru.vsu.cs.dzhabbarov.cinema.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vsu.cs.dzhabbarov.cinema.dto.AuthRequestDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.AuthResponseDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.RegisterRequestDto;
import ru.vsu.cs.dzhabbarov.cinema.exception.ExistUserException;
import ru.vsu.cs.dzhabbarov.cinema.exception.IncorrectCredentialsException;
import ru.vsu.cs.dzhabbarov.cinema.exception.NotExistUserException;
import ru.vsu.cs.dzhabbarov.cinema.user.Role;
import ru.vsu.cs.dzhabbarov.cinema.entity.UserEntity;
import ru.vsu.cs.dzhabbarov.cinema.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthRequestDto register(RegisterRequestDto request) throws DataIntegrityViolationException{
        Optional<UserEntity> optionalUser = repository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new ExistUserException();
        }

        var user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return convertEntityToDto(user, jwtToken);
    }

    public AuthRequestDto authenticate(AuthResponseDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new IncorrectCredentialsException();
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(NotExistUserException::new);

        var jwtToken = jwtService.generateToken(user);
        return convertEntityToDto(user, jwtToken);
    }

    private static AuthRequestDto convertEntityToDto(UserEntity userEntity, String token) {
        return AuthRequestDto.builder()
                .name(userEntity.getFirstname() + ' ' + userEntity.getLastname())
                .email(userEntity.getEmail())
                .role(userEntity.getRole().name())
                .token(token)
                .build();
    }
}
