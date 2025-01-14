package com.dzydowicz.calculations.application.service;

import com.dzydowicz.calculations.application.CalculationsServicePort;
import org.springframework.stereotype.Service;

@Service
class CalculationsService implements CalculationsServicePort {

    private static final Integer VELOCITY = 17; //km/s

    @Override
    public Double calculateKineticEnergy(Integer mass) {
        return 0.5 * mass * Math.pow(VELOCITY, 2);
    }

}
