package com.dzydowicz.webflux.application;

import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyResponse;
import reactor.core.publisher.Mono;

public interface CalculationsServicePort {
    Mono<CalculateKineticEnergyResponse> calculateKineticEnergy(CalculateKineticEnergyRequest request);
}
