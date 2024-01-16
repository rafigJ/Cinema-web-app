package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface TicketService {

    Page<TicketDto> getTickets(Pageable pageable);

    Long getCurrentProfitForSoldTicketsByPeriod(LocalDateTime start, LocalDateTime end);

}
