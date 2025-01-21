package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLanding;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingFilter;
import com.dzydowicz.webflux.domain.repository.MeteoriteLandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MeteoriteLandingSpringDataRepository implements MeteoriteLandingRepository {

    private final MeteoriteLandingCrudRepository meteoriteLandingCrudRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Mono<MeteoriteLanding> save(MeteoriteLanding meteoriteLanding) {
        return Mono.just(meteoriteLanding)
                .map(MeteoriteLandingEntityConverter::toEntity)
                .flatMap(meteoriteLandingCrudRepository::save)
                .map(MeteoriteLandingEntityConverter::toDomain);
    }

    @Override
    public Mono<MeteoriteLanding> getMeteoriteLanding(Long id) {
        return meteoriteLandingCrudRepository.findMeteoriteLandingById(id)
                .map(MeteoriteLandingEntityConverter::toDomain);
    }

    @Override
    public Flux<MeteoriteLanding> getMeteoriteLandings(MeteoriteLandingFilter meteoriteLandingFilter) {
        return r2dbcEntityTemplate.select(
                Query.query(buildFilterCriteria(meteoriteLandingFilter)), MeteoriteLandingEntity.class)
                .map(MeteoriteLandingEntityConverter::toDomain);
    }

    @Override
    public Mono<Void> remove(Long id) {
        return meteoriteLandingCrudRepository.deleteById(id);
    }

    private Criteria buildFilterCriteria(MeteoriteLandingFilter filter) {
        Criteria criteria = Criteria.empty();

        if (filter.ids() != null && !filter.ids().isEmpty()) {
            criteria = criteria.and("id").in(filter.ids());
        }
        if (filter.nameType() != null) {
            criteria = criteria.and("nameType").is(filter.nameType());
        }
        if (filter.fall() != null) {
            criteria = criteria.and("fall").is(filter.fall());
        }
        if (filter.minMass() != null) {
            criteria = criteria.and("mass").greaterThanOrEquals(filter.minMass());
        }
        if (filter.maxMass() != null) {
            criteria = criteria.and("mass").lessThanOrEquals(filter.maxMass());
        }
        if (filter.minYear() != null) {
            criteria = criteria.and("year").greaterThanOrEquals(filter.minYear());
        }
        if (filter.maxYear() != null) {
            criteria = criteria.and("year").lessThanOrEquals(filter.maxYear());
        }

        return criteria;
    }
}
