package ru.vsu.cs.dzhabbarov.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class FilmEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "description")
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToMany()
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genres;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (year != that.year) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return posterUrl.equals(that.posterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
