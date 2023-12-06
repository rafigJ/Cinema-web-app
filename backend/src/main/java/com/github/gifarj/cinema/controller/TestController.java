package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

// TODO УДАЛИТЬ ТЕСТОВЫЙ КОНТРОЛЛЕР
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final HallRepository hallRepository;
    private final FilmRepository films;

    @GetMapping()
    public String answer() {
        var sessions = films.findById(243).get().getSessions();
        return sessions.get(0).getId() + " " + sessions.get(0).getPrice() + " hall:" + userRepository.getReferenceById(UUID.fromString("be01cc1f-8a5a-4658-9b9f-2212976da607"));
    }
}
