package com.github.gifarj.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
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
