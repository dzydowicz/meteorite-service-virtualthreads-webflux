package com.dzydowicz.webflux.application.service;

import com.dzydowicz.webflux.application.MeteoriteLandingServicePort;
import com.dzydowicz.webflux.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.webflux.domain.model.MeteoriteLanding;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingFilter;
import com.dzydowicz.webflux.domain.repository.MeteoriteLandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MeteoriteLandingService implements MeteoriteLandingServicePort {

    private final MeteoriteLandingRepository meteoriteLandingRepository;

    @Override
    public Mono<MeteoriteLandingDTO> getMeteoriteLanding(Long id) {
        return meteoriteLandingRepository.getMeteoriteLanding(id) //TODO change exception
                .switchIfEmpty(Mono.error(new RuntimeException("Meteorite landing with id " + id + " not found")))
                .map(MeteoriteLandingDTO::from);
    }

    @Override
    public Flux<MeteoriteLandingDTO> getMeteoriteLandings(MeteoriteLandingFilterDTO filterDTO) {
        MeteoriteLandingFilter filter = new MeteoriteLandingFilter(
                filterDTO.getIds(),
                filterDTO.getNameType(),
                filterDTO.getFall(),
                filterDTO.getMinMass(),
                filterDTO.getMaxMass(),
                filterDTO.getMinYear(),
                filterDTO.getMaxYear()
        );

        return meteoriteLandingRepository.getMeteoriteLandings(filter)
                .map(MeteoriteLandingDTO::from);
    }

    @Override
    public Mono<MeteoriteLandingDTO> createMeteoriteLanding(CreateMeteoriteLandingRequest request) {
        MeteoriteLanding meteoriteLandingToSave = new MeteoriteLanding(
                null,
                request.getName(),
                request.getNameType(),
                request.getRecClass(),
                request.getMass(),
                request.getFall(),
                request.getYear(),
                request.getRecLat(),
                request.getRecLong(),
                request.getGeolocation()
        );

        return meteoriteLandingRepository.save(meteoriteLandingToSave)
                .map(MeteoriteLandingDTO::from);
    }

    @Override
    public Mono<Void> removeMeteoriteLanding(Long id) {
        return meteoriteLandingRepository.remove(id);
    }
}
