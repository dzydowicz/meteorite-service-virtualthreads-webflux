package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLandingFilter;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
interface MeteoriteLandingCrudRepository extends ReactiveCrudRepository<MeteoriteLandingEntity, Long> {

    @Query("SELECT * FROM meteorite_landings WHERE id = :id")
    Mono<MeteoriteLandingEntity> findMeteoriteLandingById(Long id);

    @Query("""
        SELECT * FROM meteorite_landings 
        WHERE (:#{#filter.ids} IS NULL OR id IN (:#{#filter.ids}))
          AND (:#{#filter.nameType} IS NULL OR nameType = :#{#filter.nameType})
          AND (:#{#filter.fall} IS NULL OR fall = :#{#filter.fall})
          AND (:#{#filter.minMass} IS NULL OR mass >= :#{#filter.minMass})
          AND (:#{#filter.maxMass} IS NULL OR mass <= :#{#filter.maxMass})
          AND (:#{#filter.minYear} IS NULL OR year >= :#{#filter.minYear})
          AND (:#{#filter.maxYear} IS NULL OR year <= :#{#filter.maxYear})
    """)
    Flux<MeteoriteLandingEntity> findMeteoriteLandings(MeteoriteLandingFilter filter);
}
