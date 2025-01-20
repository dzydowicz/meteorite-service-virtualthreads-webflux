package com.dzydowicz.virtualthreads.domain.repository;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLanding;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFilter;

import java.util.List;

public interface MeteoriteLandingRepository {
    MeteoriteLanding save(MeteoriteLanding meteoriteLanding);
    MeteoriteLanding getMeteoriteLanding(Long id);
    List<MeteoriteLanding> getMeteoriteLandings(MeteoriteLandingFilter meteoriteLandingFilter);
    void remove(Long id);
}
