package com.github.gifarj.cinema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private HallEntity hall;

    @Column(name = "a_date", nullable = false)
    private LocalDate date;

    @Column(name = "a_time", nullable = false)
    private LocalTime time;

    @Column(name = "price", nullable = false)
    private Integer price;
}
