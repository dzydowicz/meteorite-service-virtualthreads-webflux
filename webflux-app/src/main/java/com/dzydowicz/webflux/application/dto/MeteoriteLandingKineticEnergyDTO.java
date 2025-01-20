package com.dzydowicz.webflux.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MeteoriteLandingKineticEnergyDTO {
    private Long id;
    private String name;
    private Float mass;
    private Float kineticEnergy;
}
