package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.AuthRequestDto;
import com.github.gifarj.cinema.dto.AuthResponseDto;
import com.github.gifarj.cinema.dto.RegisterRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.gifarj.cinema.auth.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthRequestDto> register(
           @Valid @RequestBody RegisterRequestDto request
    ) {
        var register = service.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRequestDto> login(
            @RequestBody AuthResponseDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
