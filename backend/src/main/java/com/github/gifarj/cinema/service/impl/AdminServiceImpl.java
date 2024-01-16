package com.github.gifarj.cinema.service.impl;

import com.github.gifarj.cinema.dto.UserDto;
import com.github.gifarj.cinema.entity.UserEntity;
import com.github.gifarj.cinema.exception.BadRequestException;
import com.github.gifarj.cinema.exception.NotExistUserException;
import com.github.gifarj.cinema.exception.RestException;
import com.github.gifarj.cinema.repository.UserRepository;
import com.github.gifarj.cinema.service.AdminService;
import com.github.gifarj.cinema.user.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        return userEntities.map(e -> modelMapper.map(e, UserDto.class));
    }

    @Override
    public UserDto updateRoleUser(UUID userUuid, Role role) {
        UserEntity user = userRepository.findById(userUuid).orElseThrow(NotExistUserException::new);
        user.setUpdateTime(LocalDateTime.now());
        user.setRole(role);
        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Client error: " + e.getMessage());
        } catch (RuntimeException e) {
            throw new RestException("Server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return modelMapper.map(user, UserDto.class);
    }
}
