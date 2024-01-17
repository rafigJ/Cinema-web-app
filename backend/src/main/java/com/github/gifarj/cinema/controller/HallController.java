package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.HallDto;
import com.github.gifarj.cinema.service.HallService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/halls")
public class HallController {
    private final HallService service;

    @GetMapping()
    public Page<HallDto> getHalls(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return service.getHalls(PageRequest.of(page, limit));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HallDto createHall(@Valid @RequestBody HallDto hallDto) {
        return service.createHall(hallDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HallDto updateHall(@NotNull @PathVariable Integer id,
                                  @Valid @RequestBody HallDto hallDto) {
        return service.updateHall(id, hallDto);
    }

    @DeleteMapping("/{id}")
    public void deleteHall(@NotNull @PathVariable Integer id) {
        service.deleteHall(id);
    }
}
