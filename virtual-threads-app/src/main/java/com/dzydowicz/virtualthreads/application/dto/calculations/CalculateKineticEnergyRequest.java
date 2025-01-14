package com.dzydowicz.virtualthreads.application.dto.calculations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateKineticEnergyRequest {
    private Integer mass;
}
