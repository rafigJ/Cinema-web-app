package com.github.gifarj.cinema.service;

import com.github.gifarj.cinema.dto.UserDto;
import com.github.gifarj.cinema.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdminService {

    Page<UserDto> getUsers(Pageable pageable);

    UserDto updateRoleUser(UUID userUuid, Role role);

}
