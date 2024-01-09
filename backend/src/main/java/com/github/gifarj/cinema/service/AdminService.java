package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.UserDto;
import com.github.gifarj.cinema.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AdminService {

    Page<UserDto> getUsers(Pageable pageable);

    UserDto updateRoleUser(UUID userUuid, Role role);

    Page<TicketDto> getTickets(Pageable pageable);

    Long getCurrentProfitForSoldTicketsByPeriod(LocalDateTime start, LocalDateTime end);

}
