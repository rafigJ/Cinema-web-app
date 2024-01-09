package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.auth.AuthResponseDto;
import com.github.gifarj.cinema.dto.auth.AuthRequestDto;
import com.github.gifarj.cinema.dto.auth.RegisterRequestDto;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.github.gifarj.cinema.auth.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
           @Valid @RequestBody RegisterRequestDto request
    ) {
        var register = service.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
           @Valid @RequestBody AuthRequestDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping()
    public ResponseEntity<AuthResponseDto> getUserCredentials(
            @RequestHeader String authorization,
            @AuthenticationPrincipal User user
    ) {
        if (!authorization.startsWith("Bearer ")) {
            throw new RestException("Invalid header 'Bearer '", HttpStatus.BAD_REQUEST);
        }
        if (user == null) {
            throw new RestException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        String token = authorization.substring(7);
        var userEntity = user.getUserEntity();
        return ResponseEntity.ok(
            AuthResponseDto.builder()
                    .email(userEntity.getEmail())
                    .name(userEntity.getName())
                    .role(userEntity.getRole().name())
                    .token(token)
                    .build()
        );
    }
}
