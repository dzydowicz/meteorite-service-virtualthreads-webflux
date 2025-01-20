package com.dzydowicz.virtualthreads.application;

import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingKineticEnergyDTO;

import java.util.List;

public interface MeteoriteLandingsCalculationsServicePort {
    MeteoriteLandingKineticEnergyDTO getMeteoriteLandingWithKineticEnergy(Long id);
    List<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingsWithKineticEnergyList(MeteoriteLandingFilterDTO filterDTO);
}
