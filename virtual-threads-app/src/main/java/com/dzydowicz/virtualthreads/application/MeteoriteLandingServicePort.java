package com.dzydowicz.virtualthreads.application;

import com.dzydowicz.virtualthreads.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingFilterDTO;

import java.util.List;

public interface MeteoriteLandingServicePort {
    MeteoriteLandingDTO getMeteoriteLanding(Long id);
    List<MeteoriteLandingDTO> getMeteoriteLandings(MeteoriteLandingFilterDTO filter);
    MeteoriteLandingDTO createMeteoriteLanding(CreateMeteoriteLandingRequest createMeteoriteLandingRequest);
    void removeMeteoriteLanding(Long id);
}
