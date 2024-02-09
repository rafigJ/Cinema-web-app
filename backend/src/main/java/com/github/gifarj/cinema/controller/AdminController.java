package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.PageDto;
import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.UserDto;
import com.github.gifarj.cinema.exception.BadRequestException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.service.AdminService;
import com.github.gifarj.cinema.service.TicketService;
import com.github.gifarj.cinema.user.Role;
import com.github.gifarj.cinema.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Map;
import java.util.UUID;
@Tag(name = "Admin", description = "admin dashboard")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final TicketService ticketService;

    @GetMapping("/users")
    public PageDto<UserDto> getUsers(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        Page<UserDto> usersPage = adminService.getUsers(PageRequest.of(page, limit, Sort.by("createdTime")));
        return PageDto.of(usersPage);
    }

    @PatchMapping("/users/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateRoleUser(@AuthenticationPrincipal User admin,
                                  @PathVariable("uuid") String strUuid,
                                  @RequestBody UserDto user) {
        if (admin == null) {
            throw new RestException("Unauthorized admin", HttpStatus.UNAUTHORIZED);
        }

        UUID uuid = UUID.fromString(strUuid);
        if (admin.getUserEntity().getUuid().equals(uuid)) {
            throw new BadRequestException("Admin can't change themselves role");
        }

        try {
            Role role = user.getRole();
            return adminService.updateRoleUser(uuid, role);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Role must be 'admin' or 'user'");
        }
    }

    @GetMapping("/tickets")
    public PageDto<TicketDto> getTickets(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        Page<TicketDto> ticketsPage = ticketService.getTickets(PageRequest.of(page, limit));
        return PageDto.of(ticketsPage);
    }

    @GetMapping("/tickets/profit")
    public Map<String, Long> getProfit(@RequestParam(value = "start")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                       @RequestParam(value = "end")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (end.isBefore(start) && !end.isEqual(start)) {
            throw new BadRequestException("start must be before end");
        }
        return Map.of("profit",
                ticketService.getProfitByPeriod(start.atStartOfDay(), end.atStartOfDay())
        );
    }

    @GetMapping("/tickets/year-profit")
    public Map<Month, Integer> getProfitByYear(@RequestParam(value = "year") Year year) {
        return ticketService.getProfitStatisticByYear(year);
    }
}
