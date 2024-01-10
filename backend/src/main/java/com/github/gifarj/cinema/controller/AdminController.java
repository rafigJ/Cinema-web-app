package com.github.gifarj.cinema.controller;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.UserDto;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.service.AdminService;
import com.github.gifarj.cinema.user.Role;
import com.github.gifarj.cinema.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    //    private final HallService hallService;
    private final AdminService adminService;

    @GetMapping("/users")
    public Page<UserDto> getUsers(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return adminService.getUsers(PageRequest.of(page, limit));
    }

    @PatchMapping("/users/{uuid}")
    public UserDto updateRoleUser(@AuthenticationPrincipal User admin,
                                  @PathVariable("uuid") String strUuid,
                                  @RequestBody UserDto user) {
        if (admin == null) {
            throw new RestException("Unauthorized admin", HttpStatus.UNAUTHORIZED);
        }

        UUID uuid = UUID.fromString(strUuid);
        if (admin.getUserEntity().getUuid().equals(uuid)) {
            throw new RestException("Admin can't change themselves role", HttpStatus.BAD_REQUEST);
        }

        try {
            Role role = user.getRole();
            return adminService.updateRoleUser(uuid, role);
        } catch (IllegalArgumentException e) {
            throw new RestException("Role must be 'admin' or 'user'", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tickets")
    public Page<TicketDto> getTickets(@RequestParam(value = "_page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "_limit", defaultValue = "10") Integer limit) {
        return adminService.getTickets(PageRequest.of(page, limit));
    }

    @GetMapping("/tickets/profit")
    public Map<String, Long> getProfit(@RequestParam(value = "start")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                       @RequestParam(value = "end")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (end.isBefore(start) && !end.isEqual(start)) {
            throw new RestException("start must be before end", HttpStatus.BAD_REQUEST);
        }
        return Map.of("profit",
                adminService.getCurrentProfitForSoldTicketsByPeriod(start.atStartOfDay(), end.atStartOfDay())
        );
    }
}
