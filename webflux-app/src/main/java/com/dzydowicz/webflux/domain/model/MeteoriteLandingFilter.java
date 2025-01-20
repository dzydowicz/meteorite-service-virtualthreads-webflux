package com.dzydowicz.webflux.domain.model;

import java.util.List;

public record MeteoriteLandingFilter(
        List<Integer> ids,
        MeteoriteLandingNameTypeEnum nameType,
        MeteoriteLandingFallEnum fall,
        Float minMass,
        Float maxMass,
        Integer minYear,
        Integer maxYear
) {}
