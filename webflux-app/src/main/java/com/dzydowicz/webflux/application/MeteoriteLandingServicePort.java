package com.dzydowicz.webflux.application;

import com.dzydowicz.webflux.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingFilterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeteoriteLandingServicePort {
    Mono<MeteoriteLandingDTO> getMeteoriteLanding(Long id);
    Flux<MeteoriteLandingDTO> getMeteoriteLandings(MeteoriteLandingFilterDTO filter);
    Mono<MeteoriteLandingDTO> createMeteoriteLanding(CreateMeteoriteLandingRequest createMeteoriteLandingRequest);
    Mono<Void> removeMeteoriteLanding(Long id);
}
