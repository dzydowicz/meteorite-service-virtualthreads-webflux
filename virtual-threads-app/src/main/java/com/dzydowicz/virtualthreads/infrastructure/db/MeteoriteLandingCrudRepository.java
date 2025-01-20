package com.dzydowicz.virtualthreads.infrastructure.db;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFilter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MeteoriteLandingCrudRepository extends CrudRepository<MeteoriteLandingEntity, Long> {

    @Query("""
        SELECT m FROM meteorite_landings m WHERE m.id = :id
    """)
    MeteoriteLandingEntity findMeteoriteLandingById(Long id);

    @Query("""
        SELECT m FROM meteorite_landings m
        WHERE (:#{#filter.ids} IS NULL OR m.id IN :#{#filter.ids})
          AND (:#{#filter.nameType} IS NULL OR m.nameType = :#{#filter.nameType})
          AND (:#{#filter.fall} IS NULL OR m.fall = :#{#filter.fall})
          AND (:#{#filter.minMass} IS NULL OR m.mass >= :#{#filter.minMass})
          AND (:#{#filter.maxMass} IS NULL OR m.mass <= :#{#filter.maxMass})
          AND (:#{#filter.minYear} IS NULL OR m.year >= :#{#filter.minYear})
          AND (:#{#filter.maxYear} IS NULL OR m.year <= :#{#filter.maxYear})
    """)
    List<MeteoriteLandingEntity> findMeteoriteLandings(@Param("filter") MeteoriteLandingFilter filter);

}
