package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

}
