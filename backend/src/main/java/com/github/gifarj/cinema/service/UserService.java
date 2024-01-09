package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    TicketDto buyTicket(UUID uuid, TicketDto ticketDto);

    Page<TicketDto> getBoughtTickets(UUID uuid, Pageable pageable);

    UserDto getUser(UUID uuid);
}
