package com.dzydowicz.calculations.application.service;

import com.dzydowicz.calculations.application.CalculationsServicePort;
import org.springframework.stereotype.Service;

@Service
class CalculationsService implements CalculationsServicePort {

    private static final Integer VELOCITY = 17; //km/s

    @Override
    public Float calculateKineticEnergy(Float mass) {
        return 0.5f * mass * (float) Math.pow(VELOCITY, 2);
    }

}
