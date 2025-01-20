package com.dzydowicz.webflux.domain.model;

import lombok.Getter;

@Getter
public enum MeteoriteLandingFallEnum {
    FELL("Fell"),
    FOUND("Found");

    private final String label;

    MeteoriteLandingFallEnum(String label) {
        this.label = label;
    }

    public static MeteoriteLandingFallEnum fromLabel(String label) {
        for (MeteoriteLandingFallEnum value : values()) {
            if (value.getLabel().equalsIgnoreCase(label)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
