package ru.vsu.cs.dzhabbarov.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<FilmEntity> films;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


