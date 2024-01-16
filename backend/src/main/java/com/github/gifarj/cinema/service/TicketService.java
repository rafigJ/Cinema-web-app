package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Map;

public interface TicketService {

    Page<TicketDto> getTickets(Pageable pageable);

    Long getProfitByPeriod(LocalDateTime start, LocalDateTime end);

    Map<Month, Integer> getProfitStatisticByYear(Year year);
}
