package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.TicketDto;
import com.github.gifarj.cinema.dto.user.UserDto;
import com.github.gifarj.cinema.entity.SessionEntity;
import com.github.gifarj.cinema.entity.TicketEntity;
import com.github.gifarj.cinema.entity.UserEntity;
import com.github.gifarj.cinema.exception.NotExistUserException;
import com.github.gifarj.cinema.exception.NotFoundException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.SessionRepository;
import com.github.gifarj.cinema.repository.TicketRepository;
import com.github.gifarj.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository repository;
    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketDto buyTicket(UUID uuid, TicketDto ticketDto) {
        UserEntity user = repository.findForUpdateByUuid(uuid)
                .orElseThrow(NotExistUserException::new);
        TicketEntity entity = convertToEntity(ticketDto);
        SessionEntity session = entity.getSession();

        if (user.getMoney() < session.getPrice()) {
            throw new RestException("insufficient funds", HttpStatus.PAYMENT_REQUIRED);
        }

        // TODO проверить то, что row < hall_rows, column < column_rows. Переименовать column в a_column или col
        // TODO добавить проверку на уникальность row, col и session (в бд а также тут)

        user.setMoney(user.getMoney() - session.getPrice());
        entity.setOwner(user);
        entity.setBuyTime(LocalDateTime.now());

        try {
            repository.save(user);
            TicketEntity ticket = ticketRepository.saveAndFlush(entity);
            return convertToDto(ticket);
        } catch (DataIntegrityViolationException e) {
            throw new RestException("Client error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<TicketDto> getBoughtTickets(UUID uuid, Pageable pageable) {
        UserEntity user = repository.findById(uuid).orElseThrow(NotExistUserException::new);
        Page<TicketEntity> tickets = ticketRepository.findAllByOwner(user, pageable);
        return tickets.map(this::convertToDto);
    }

    @Override
    public UserDto getUser(UUID uuid) {
        var user = repository.findById(uuid).orElseThrow(()
                -> new RestException("Server error", HttpStatus.INTERNAL_SERVER_ERROR)
        );
        return modelMapper.map(user, UserDto.class);
    }

    private TicketDto convertToDto(TicketEntity entity) {
        TicketDto dto = modelMapper.map(entity, TicketDto.class);
        dto.setSessionId(entity.getSession().getId());
        dto.setUserUuid(entity.getOwner().getUuid());
        return dto;
    }

    private TicketEntity convertToEntity(TicketDto dto) {
        TicketEntity entity = modelMapper.map(dto, TicketEntity.class);
        Integer sessionId = dto.getSessionId();
        SessionEntity session = sessionRepository.findById(sessionId).orElseThrow(() ->
                new NotFoundException("Session by id: " + sessionId + " not found")
        );
        entity.setSession(session);
        return entity;
    }
}
