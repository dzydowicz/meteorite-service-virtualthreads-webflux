package com.dzydowicz.webflux.application.dto.calculations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateKineticEnergyRequest {
    private Float mass;
}
