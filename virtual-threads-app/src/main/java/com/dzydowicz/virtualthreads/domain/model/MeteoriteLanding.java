package com.dzydowicz.virtualthreads.domain.model;

public record MeteoriteLanding(
        Long id,
        String name,
        MeteoriteLandingNameTypeEnum nameType,
        String recClass,
        Float mass,
        MeteoriteLandingFallEnum fall,
        Integer year,
        Float recLat,
        Float recLong,
        String geolocation
) {}
