package com.github.gifarj.cinema.repository;

import com.github.gifarj.cinema.entity.TicketEntity;
import com.github.gifarj.cinema.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    Page<TicketEntity> findAllByOwner(UserEntity owner, Pageable pageable);

    List<TicketEntity> findAllBySessionId(Integer sessionId);

    List<TicketEntity> findAllByBuyTimeBetween(LocalDateTime start, LocalDateTime end);


    /**
     * Используется для статистики за год.
     * Возвращает список кортежей (массивов, размера 2)
     * Пример возвращаемого массива: arr[0] = 5 (номер месяца), arr[1] = 1000 (прибыль)
     * @return list of records (array) type: Month number[0] | Profit[1]
     */
    @Query("SELECT MONTH(t.buyTime) as month, SUM(s.price) as totalRevenue " +
            "FROM TicketEntity t " +
            "JOIN t.session s " +
            "WHERE t.buyTime BETWEEN :start AND :end " +
            "GROUP BY MONTH(t.buyTime)")
    List<Integer[]> calculateProfitByMonthPeriod(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
