package com.dzydowicz.webflux.application;

import com.dzydowicz.webflux.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingKineticEnergyDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeteoriteLandingsCalculationsServicePort {
    Mono<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingWithKineticEnergy(Long id);
    Flux<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingsWithKineticEnergyList(MeteoriteLandingFilterDTO filterDTO);
}
