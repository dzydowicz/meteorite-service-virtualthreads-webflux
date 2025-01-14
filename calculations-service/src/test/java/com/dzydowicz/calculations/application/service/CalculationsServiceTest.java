package com.dzydowicz.calculations.application.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationsServiceTest {

    private final CalculationsService calculationsService = new CalculationsService();

    @Test
    void testCalculateKineticEnergy_smallMass() {
        // Given
        int mass = 1000; // in grams

        // When
        double result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2); // KE = 0.5 * m * v^2
        assertEquals(expected, result, "The calculated impact energy is incorrect for small mass.");
    }

    @Test
    void testCalculateKineticEnergy_largeMass() {
        // Given
        int mass = 2000000; // in grams

        // When
        double result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for large mass.");
    }

    @Test
    void testCalculateKineticEnergy_zeroMass() {
        // Given
        int mass = 0; // Zero mass

        // When
        double result = calculationsService.calculateKineticEnergy(mass);

        // Then
        assertEquals(0, result, "The calculated impact energy should be zero for zero mass.");
    }

    @Test
    void testCalculateKineticEnergy_negativeMass() {
        // Given
        int mass = -1000; // Negative mass

        // When
        double result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for negative mass.");
    }

    @Test
    void testCalculateKineticEnergy_boundaryMass() {
        // Given
        int mass = Integer.MAX_VALUE; // Maximum integer value

        // When
        double result = calculationsService.calculateKineticEnergy(mass);

        // Then
        double expected = 0.5 * mass * Math.pow(17, 2);
        assertEquals(expected, result, "The calculated impact energy is incorrect for boundary mass.");
    }
}
