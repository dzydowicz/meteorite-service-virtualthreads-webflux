package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLanding;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingFilter;
import com.dzydowicz.webflux.domain.repository.MeteoriteLandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MeteoriteLandingSpringDataRepository implements MeteoriteLandingRepository {

    private final MeteoriteLandingCrudRepository meteoriteLandingCrudRepository;

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
        return meteoriteLandingCrudRepository.findMeteoriteLandings(meteoriteLandingFilter)
                .map(MeteoriteLandingEntityConverter::toDomain);
    }

    @Override
    public Mono<Void> remove(Long id) {
        return meteoriteLandingCrudRepository.deleteById(id);
    }
}
