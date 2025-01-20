package com.dzydowicz.virtualthreads.application.service;

import com.dzydowicz.virtualthreads.application.MeteoriteLandingServicePort;
import com.dzydowicz.virtualthreads.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLanding;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFilter;
import com.dzydowicz.virtualthreads.domain.repository.MeteoriteLandingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class MeteoriteLandingService implements MeteoriteLandingServicePort {

    private final MeteoriteLandingRepository meteoriteLandingRepository;

    @Override
    public MeteoriteLandingDTO getMeteoriteLanding(Long id) {
        MeteoriteLanding meteoriteLanding = meteoriteLandingRepository.getMeteoriteLanding(id);
        if (meteoriteLanding == null) {
            throw new EntityNotFoundException("Meteorite landing with id " + id + " not found"); //TODO change exception
        }

        return MeteoriteLandingDTO.from(meteoriteLanding);
    }

    @Override
    public List<MeteoriteLandingDTO> getMeteoriteLandings(MeteoriteLandingFilterDTO filterDTO) {
        MeteoriteLandingFilter filter = new MeteoriteLandingFilter(
                filterDTO.getIds(),
                filterDTO.getNameType(),
                filterDTO.getFall(),
                filterDTO.getMinMass(),
                filterDTO.getMaxMass(),
                filterDTO.getMinYear(),
                filterDTO.getMaxYear()
        );

        return meteoriteLandingRepository.getMeteoriteLandings(filter).stream()
                .map(MeteoriteLandingDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public MeteoriteLandingDTO createMeteoriteLanding(CreateMeteoriteLandingRequest request) {
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

        MeteoriteLanding meteoriteLanding = meteoriteLandingRepository.save(meteoriteLandingToSave);
        return MeteoriteLandingDTO.from(meteoriteLanding);
    }

    @Override
    public void removeMeteoriteLanding(Long id) {
        meteoriteLandingRepository.remove(id);
    }
}
