package com.dzydowicz.webflux.domain.repository;

import com.dzydowicz.webflux.domain.model.MeteoriteLanding;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeteoriteLandingRepository {
    Mono<MeteoriteLanding> save(MeteoriteLanding meteoriteLanding);
    Mono<MeteoriteLanding> getMeteoriteLanding(Long id);
    Flux<MeteoriteLanding> getMeteoriteLandings(MeteoriteLandingFilter meteoriteLandingFilter);
    Mono<Void> remove(Long id);
}
