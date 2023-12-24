package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.user.UserDto;
import com.github.gifarj.cinema.service.UserService;
import com.github.gifarj.cinema.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping()
    private UserDto getUser(@AuthenticationPrincipal User user) {
        return service.getUser(user.getUserEntity().getUuid());
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.OK)
    public TicketDto buyTicket(@AuthenticationPrincipal User user, @Valid @RequestBody TicketDto ticketDto) {
        return service.buyTicket(user.getUserEntity().getUuid(), ticketDto);
    }

    @GetMapping("/tickets")
    public Page<TicketDto> getBoughtTickets(@AuthenticationPrincipal User user,
                                            @RequestParam(value = "_page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return service.getBoughtTickets(user.getUserEntity().getUuid(), PageRequest.of(page, limit));
    }
}
