package com.dzydowicz.webflux.domain.model;

import lombok.Getter;

@Getter
public enum MeteoriteLandingNameTypeEnum {
    RELICT("Relict"),
    VALID("Valid");

    private final String label;

    MeteoriteLandingNameTypeEnum(String label) {
        this.label = label;
    }

    public static MeteoriteLandingNameTypeEnum fromLabel(String label) {
        for (MeteoriteLandingNameTypeEnum value : values()) {
            if (value.getLabel().equalsIgnoreCase(label)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
