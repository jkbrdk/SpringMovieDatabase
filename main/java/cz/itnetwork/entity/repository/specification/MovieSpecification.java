package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.*;
import cz.itnetwork.entity.filter.MovieFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MovieSpecification implements Specification<MovieEntity> {

    private final MovieFilter filter;

    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getFromYear() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(MovieEntity_.YEAR), filter.getFromYear()));
        }

        if(filter.getToYear() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(MovieEntity_.YEAR), filter.getToYear()));
        }

        if(filter.getDirectorID() != null){
            Join<PersonEntity, MovieEntity> directorJoin = root.join(MovieEntity_.DIRECTOR);
            predicates.add(criteriaBuilder.equal(directorJoin.get(PersonEntity_.ID), filter.getDirectorID()));
        }

        if(filter.getActorID() != null){
            Join<PersonEntity, MovieEntity> actorJoin = root.join(MovieEntity_.ACTORS);
            predicates.add(actorJoin.get(PersonEntity_.ID).in(filter.getActorID()));
        }

        if(filter.getGenre() != null){
            Expression<String> genreJoin = root.join(MovieEntity_.GENRES);
            predicates.add(genreJoin.in(filter.getGenre()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
