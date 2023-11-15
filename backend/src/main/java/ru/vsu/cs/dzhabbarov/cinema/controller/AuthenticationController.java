package ru.vsu.cs.dzhabbarov.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dzhabbarov.cinema.auth.AuthenticationRequest;
import ru.vsu.cs.dzhabbarov.cinema.auth.AuthenticationResponse;
import ru.vsu.cs.dzhabbarov.cinema.auth.AuthenticationService;
import ru.vsu.cs.dzhabbarov.cinema.auth.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        var register = service.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
