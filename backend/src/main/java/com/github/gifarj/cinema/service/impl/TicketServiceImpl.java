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
import java.util.List;

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
    public Long getCurrentProfitForSoldTicketsByPeriod(LocalDateTime start, LocalDateTime end) {
        List<TicketEntity> soldTickets = ticketRepository.findAllByBuyTimeBetween(start, end);
        Long profit = 0L;
        for (TicketEntity soldTicket : soldTickets) {
            profit += soldTicket.getSession().getPrice();
        }
        return profit;
    }


}
