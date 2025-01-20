package com.dzydowicz.webflux.application.dto;

import com.dzydowicz.webflux.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeteoriteLandingFilterDTO {
    private List<Integer> ids;
    private MeteoriteLandingNameTypeEnum nameType;
    private MeteoriteLandingFallEnum fall;
    private Float minMass;
    private Float maxMass;
    private Integer minYear;
    private Integer maxYear;
}
