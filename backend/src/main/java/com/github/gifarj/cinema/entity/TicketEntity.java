package com.github.gifarj.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Column(name = "row", nullable = false)
    private Short row;

    @Column(name = "column", nullable = false)
    private Short column;

    @Column(name = "buy_time", nullable = false)
    private LocalDateTime buyTime;
}
