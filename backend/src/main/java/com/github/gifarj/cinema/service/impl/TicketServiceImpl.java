package com.github.gifarj.cinema.service.impl;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.entity.TicketEntity;
import com.github.gifarj.cinema.repository.TicketRepository;
import com.github.gifarj.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<TicketDto> getTickets(Pageable pageable) {
        Page<TicketEntity> ticketEntities = ticketRepository.findAll(pageable);
        return ticketEntities.map(e -> {
            TicketDto dto = modelMapper.map(e, TicketDto.class);
            dto.setSessionId(e.getSession().getId());
            dto.setUserUuid(e.getOwner().getUuid());
            return dto;
        });
    }

    @Override
    public Long getProfitByPeriod(LocalDateTime start, LocalDateTime end) {
        List<TicketEntity> soldTickets = ticketRepository.findAllByBuyTimeBetween(start, end);
        Long profit = 0L;
        for (TicketEntity soldTicket : soldTickets) {
            profit += soldTicket.getSession().getPrice();
        }
        return profit;
    }

    @Override
    public Map<Month, Integer> getProfitStatisticByYear(Year year) {
        Map<Month, Integer> statistic = new EnumMap<>(Month.class);
        LocalDateTime start = LocalDateTime.of(year.getValue(), Month.JANUARY, 1, 0, 0);
        LocalDateTime end;
        if (year.isBefore(Year.now())) {
            end = LocalDateTime.of(year.getValue(), Month.DECEMBER, 31, 23, 59);
        } else {
            end = LocalDateTime.now();
        }
        List<Integer[]> profitByMonthPeriod = ticketRepository.calculateProfitByMonthPeriod(start, end);
        for (Integer[] data : profitByMonthPeriod) {
            statistic.put(Month.of(data[0]), data[1]);
        }
        return statistic;
    }

}
