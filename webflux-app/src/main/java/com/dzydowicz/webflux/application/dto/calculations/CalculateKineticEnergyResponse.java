package com.dzydowicz.webflux.application.dto.calculations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateKineticEnergyResponse {
    private Float kineticEnergy;
}
