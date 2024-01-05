package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.SessionDto;
import com.github.gifarj.cinema.service.SessionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions")
public class SessionController {

    private final SessionService service;

    @GetMapping("/{id}")
    private SessionDto getById(@PathVariable("id") Integer id) {
        return service.getSessionById(id);
    }

    @GetMapping()
    public Page<SessionDto> getSessions(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "_limit", defaultValue = "10") Integer limit,
                                        @RequestParam(value = "start", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                        @RequestParam(value = "end", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getSessions(PageRequest.of(page, limit), start, end);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDto create(@Valid @RequestBody SessionDto sessionDto) {
        return service.createSession(sessionDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SessionDto update(@NotNull @PathVariable Integer id,
                             @Valid @RequestBody SessionDto sessionDto) {
        return service.updateSession(id, sessionDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@NotNull @PathVariable Integer id) {
        service.deleteSession(id);
    }
}