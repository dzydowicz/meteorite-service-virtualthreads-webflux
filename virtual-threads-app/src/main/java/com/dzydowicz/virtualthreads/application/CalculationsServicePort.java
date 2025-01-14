package com.dzydowicz.virtualthreads.application;

import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyResponse;

public interface CalculationsServicePort {
    CalculateKineticEnergyResponse calculateKineticEnergy(CalculateKineticEnergyRequest request);
}
