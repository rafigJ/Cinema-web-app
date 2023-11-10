package ru.vsu.cs.dzhabbarov.cinema.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.dzhabbarov.cinema.user.User;

@RestController
@RequestMapping("/api/v1/film-controller")
public class FilmController {

    @GetMapping
    public ResponseEntity<User> filmHello(@AuthenticationPrincipal User principal) {
        return ResponseEntity.ok(principal);
    }

}
