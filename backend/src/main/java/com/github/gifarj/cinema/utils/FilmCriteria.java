package com.github.gifarj.cinema.utils;

import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.entity.GenreEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class FilmCriteria {
    private final String filmName;
    private final Collection<Integer> genreIds;

    private FilmCriteria(String filmName, Collection<Integer> genreIds) {
        if (StringUtils.isEmpty(filmName) && CollectionUtils.isEmpty(genreIds)) {
            throw new IllegalArgumentException("filmName or genreIds must not be null (empty)");
        }
        this.filmName = filmName;
        this.genreIds = genreIds;
    }

    public static FilmCriteria of(String filmName, Collection<Integer> genreIds) {
        return new FilmCriteria(filmName, genreIds);
    }

    public Specification<FilmEntity> getSpecification() {
        if (StringUtils.isNotEmpty(filmName) && CollectionUtils.isNotEmpty(genreIds)) {
            // Если заданы и имя фильма, и жанры
            return genresIdInAndNameContainsIgnoreCase(genreIds, filmName);
        } else if (StringUtils.isNotEmpty(filmName)) {
            // Если задано только имя фильма
            return nameContainsIgnoreCase(filmName);
        } else if (CollectionUtils.isNotEmpty(genreIds)) {
            // Если заданы только жанры
            return genresIdIn(genreIds);
        } else {
            throw new IllegalArgumentException("filmName or genreIds must be provided");
        }
    }


    private static Specification<FilmEntity> nameContainsIgnoreCase(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<FilmEntity> genresIdIn(Collection<Integer> genreIds) {
        return (root, query, criteriaBuilder) -> {
            Join<FilmEntity, GenreEntity> genreJoin = root.join("genres", JoinType.INNER);

            Predicate genrePredicate = genreJoin.get("id").in(genreIds);

            query.groupBy(root.get("id")); // Группировка по film_id
            query.having(criteriaBuilder.equal(criteriaBuilder.countDistinct(genreJoin.get("id")), genreIds.size())); // Условие HAVING

            return criteriaBuilder.and(genrePredicate);
        };
    }

    private static Specification<FilmEntity> genresIdInAndNameContainsIgnoreCase(Collection<Integer> genreIds, String name) {
        return Specification.where(genresIdIn(genreIds)).and(nameContainsIgnoreCase(name));
    }
}
