package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.UserEntity;
import com.github.gifarj.cinema.exception.NotExistUserException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findForUpdateByUuid(UUID uuid);
}
