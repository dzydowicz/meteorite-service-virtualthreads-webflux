package com.dzydowicz.virtualthreads.application.service;

import com.dzydowicz.virtualthreads.application.CalculationsServicePort;
import com.dzydowicz.virtualthreads.application.MeteoriteLandingServicePort;
import com.dzydowicz.virtualthreads.application.MeteoriteLandingsCalculationsServicePort;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingKineticEnergyDTO;
import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class MeteoriteLandingCalculationsService implements MeteoriteLandingsCalculationsServicePort {

    private final MeteoriteLandingServicePort meteoriteLandingServicePort;
    private final CalculationsServicePort calculationsServicePort;

    @Override
    public MeteoriteLandingKineticEnergyDTO getMeteoriteLandingWithKineticEnergy(Long id) {
        MeteoriteLandingDTO meteoriteLanding = meteoriteLandingServicePort.getMeteoriteLanding(id);

        CalculateKineticEnergyRequest calculateKineticEnergyRequest = new CalculateKineticEnergyRequest(meteoriteLanding.getMass());
        CalculateKineticEnergyResponse calculateKineticEnergyResponse = calculationsServicePort.calculateKineticEnergy(calculateKineticEnergyRequest);

        return MeteoriteLandingKineticEnergyDTO.builder()
                .id(meteoriteLanding.getId())
                .name(meteoriteLanding.getName())
                .mass(meteoriteLanding.getMass())
                .kineticEnergy(calculateKineticEnergyResponse.getKineticEnergy())
                .build();
    }

    @Override
    public List<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingsWithKineticEnergyList(MeteoriteLandingFilterDTO filterDTO) {
        List<MeteoriteLandingKineticEnergyDTO> result = new ArrayList<>();

        List<MeteoriteLandingDTO> meteoriteLandings = meteoriteLandingServicePort.getMeteoriteLandings(filterDTO);
        for (MeteoriteLandingDTO meteoriteLanding : meteoriteLandings) {
            CalculateKineticEnergyRequest calculateKineticEnergyRequest = new CalculateKineticEnergyRequest(meteoriteLanding.getMass());
            CalculateKineticEnergyResponse calculateKineticEnergyResponse = calculationsServicePort.calculateKineticEnergy(calculateKineticEnergyRequest);

            MeteoriteLandingKineticEnergyDTO meteoriteLandingKineticEnergyDTO = MeteoriteLandingKineticEnergyDTO.builder()
                    .id(meteoriteLanding.getId())
                    .name(meteoriteLanding.getName())
                    .mass(meteoriteLanding.getMass())
                    .kineticEnergy(calculateKineticEnergyResponse.getKineticEnergy())
                    .build();
            result.add(meteoriteLandingKineticEnergyDTO);
        }

        return result;
    }
}
