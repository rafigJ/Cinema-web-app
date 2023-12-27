package com.github.gifarj.cinema.auth;

import com.github.gifarj.cinema.dto.auth.AuthRequestDto;
import com.github.gifarj.cinema.dto.auth.AuthResponseDto;
import com.github.gifarj.cinema.dto.auth.RegisterRequestDto;
import com.github.gifarj.cinema.entity.UserEntity;
import com.github.gifarj.cinema.exception.ExistUserException;
import com.github.gifarj.cinema.exception.IncorrectCredentialsException;
import com.github.gifarj.cinema.exception.NotExistUserException;
import com.github.gifarj.cinema.repository.UserRepository;
import com.github.gifarj.cinema.user.Role;
import com.github.gifarj.cinema.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(RegisterRequestDto request) throws DataIntegrityViolationException {
        Optional<UserEntity> optionalUser = repository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new ExistUserException();
        }

        UserEntity userEntity = convertRequestToEntity(request, passwordEncoder);
        repository.save(userEntity);

        User user = new User(userEntity);
        String jwtToken = jwtService.generateToken(user);
        return convertEntityToAuthResponse(userEntity, jwtToken);
    }

    public AuthResponseDto authenticate(AuthRequestDto request) {
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
        UserEntity userEntity = repository.findByEmail(request.getEmail())
                .orElseThrow(NotExistUserException::new);

        User user = new User(userEntity);
        String jwtToken = jwtService.generateToken(user);
        return convertEntityToAuthResponse(userEntity, jwtToken);
    }

    private static AuthResponseDto convertEntityToAuthResponse(UserEntity userEntity, String token) {
        return AuthResponseDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole().name())
                .token(token)
                .build();
    }

    private static UserEntity convertRequestToEntity(RegisterRequestDto request, PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .money(1500)    // todo удалить костыль
                .build();
    }
}
