package com.dzydowicz.calculations.infrastructure.web;

import com.dzydowicz.calculations.application.CalculationsServicePort;
import com.dzydowicz.calculations.application.dto.CalculateKineticEnergyRequest;
import com.dzydowicz.calculations.application.dto.CalculateKineticEnergyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CalculationsController {

    private final CalculationsServicePort calculationsServicePort;

    @PostMapping("/calculate-kinetic-energy")
    public CalculateKineticEnergyResponse calculateKineticEnergy(@RequestBody CalculateKineticEnergyRequest request) {
        return new CalculateKineticEnergyResponse(calculationsServicePort.calculateKineticEnergy(request.getMass()));
    }

}
