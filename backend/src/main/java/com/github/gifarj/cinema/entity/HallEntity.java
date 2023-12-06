package com.github.gifarj.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hall")
public class HallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "row_count", nullable = false)
    private Short rowCount;

    @Column(name = "column_count", nullable = false)
    private Short columnCount;

    @OneToMany(mappedBy = "hall")
    private List<SessionEntity> sessions;
}
