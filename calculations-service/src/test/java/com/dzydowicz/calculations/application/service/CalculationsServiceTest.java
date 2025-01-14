package com.dzydowicz.calculations.application.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationsServiceTest {

    private final CalculationsService calculationsService = new CalculationsService();

    @Test
    void testCalculateKineticEnergy_smallMass() {
        // Given
        float mass = 10000.0f; // in grams

        // When
        float result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2); // KE = 0.5 * m * v^2
        assertEquals(expected, result, "The calculated impact energy is incorrect for small mass.");
    }

    @Test
    void testCalculateKineticEnergy_largeMass() {
        // Given
        float mass = 2000000.0f; // in grams

        // When
        float result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for large mass.");
    }

    @Test
    void testCalculateKineticEnergy_zeroMass() {
        // Given
        float mass = 0.0f; // Zero mass

        // When
        float result = calculationsService.calculateKineticEnergy(mass);

        // Then
        assertEquals(0, result, "The calculated impact energy should be zero for zero mass.");
    }

    @Test
    void testCalculateKineticEnergy_negativeMass() {
        // Given
        float mass = -1000.0f; // Negative mass

        // When
        float result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for negative mass.");
    }

    @Test
    void testCalculateKineticEnergy_boundaryMass() {
        // Given
        float mass = Integer.MAX_VALUE; // Maximum integer value

        // When
        float result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for boundary mass.");
    }
}
