package com.dzydowicz.webflux.application.service;

import com.dzydowicz.webflux.application.CalculationsServicePort;
import com.dzydowicz.webflux.application.MeteoriteLandingServicePort;
import com.dzydowicz.webflux.application.MeteoriteLandingsCalculationsServicePort;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingKineticEnergyDTO;
import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MeteoriteLandingCalculationsService implements MeteoriteLandingsCalculationsServicePort {

    private final MeteoriteLandingServicePort meteoriteLandingServicePort;
    private final CalculationsServicePort calculationsServicePort;

    @Override
    public Mono<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingWithKineticEnergy(Long id) {
        return meteoriteLandingServicePort.getMeteoriteLanding(id)
                .flatMap(this::calculateKineticEnergy);
    }

    @Override
    public Flux<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingsWithKineticEnergyList(MeteoriteLandingFilterDTO filterDTO) {
        return meteoriteLandingServicePort.getMeteoriteLandings(filterDTO)
                .flatMap(this::calculateKineticEnergy);
    }

    private Mono<MeteoriteLandingKineticEnergyDTO> calculateKineticEnergy(MeteoriteLandingDTO meteoriteLanding) {
        CalculateKineticEnergyRequest request = new CalculateKineticEnergyRequest(meteoriteLanding.getMass());
        return calculationsServicePort.calculateKineticEnergy(request)
                .map(energyResponse -> buildKineticEnergyDTO(meteoriteLanding, energyResponse));
    }

    private MeteoriteLandingKineticEnergyDTO buildKineticEnergyDTO(MeteoriteLandingDTO meteoriteLanding,
                                                                   CalculateKineticEnergyResponse energyResponse) {
        return MeteoriteLandingKineticEnergyDTO.builder()
                .id(meteoriteLanding.getId())
                .name(meteoriteLanding.getName())
                .mass(meteoriteLanding.getMass())
                .kineticEnergy(energyResponse.getKineticEnergy())
                .build();
    }
}
