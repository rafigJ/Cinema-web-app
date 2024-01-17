package com.github.gifarj.cinema.criteria;

import com.github.gifarj.cinema.entity.FilmEntity;
import com.github.gifarj.cinema.entity.GenreEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class FilmSpecifications {

    public static Specification<FilmEntity> nameContainsIgnoreCase(String name) {
        if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<FilmEntity> genresIdIn(Collection<Integer> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            Join<FilmEntity, GenreEntity> genreJoin = root.join("genres", JoinType.INNER);

            Predicate genrePredicate = genreJoin.get("id").in(genreIds);

            query.groupBy(root.get("id")); // Группировка по film_id
            query.having(criteriaBuilder.equal(criteriaBuilder.countDistinct(genreJoin.get("id")), genreIds.size())); // Условие HAVING

            return criteriaBuilder.and(genrePredicate);
        };
    }

}
