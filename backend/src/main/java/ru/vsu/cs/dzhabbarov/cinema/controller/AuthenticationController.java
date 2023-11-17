package ru.vsu.cs.dzhabbarov.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dzhabbarov.cinema.dto.AuthResponseDto;
import ru.vsu.cs.dzhabbarov.cinema.dto.AuthRequestDto;
import ru.vsu.cs.dzhabbarov.cinema.auth.AuthenticationService;
import ru.vsu.cs.dzhabbarov.cinema.dto.RegisterRequestDto;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthRequestDto> register(
            @RequestBody RegisterRequestDto request
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
