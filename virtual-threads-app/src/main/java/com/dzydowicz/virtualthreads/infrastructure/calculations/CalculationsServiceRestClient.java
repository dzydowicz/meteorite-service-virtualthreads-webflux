package com.dzydowicz.virtualthreads.infrastructure.calculations;

import com.dzydowicz.virtualthreads.application.CalculationsServicePort;
import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.virtualthreads.application.dto.calculations.CalculateKineticEnergyResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
class CalculationsServiceRestClient implements CalculationsServicePort {

    private RestTemplate restTemplate;
    private String calculationsServiceUrl;

    @Override
    public CalculateKineticEnergyResponse calculateKineticEnergy(CalculateKineticEnergyRequest request) {
        return performPostApiCall(calculationsServiceUrl, request, CalculateKineticEnergyResponse.class);
    }

    private <R, T> R performPostApiCall(String url, T request, Class<R> responseType) {
        try {
            ResponseEntity<R> response = restTemplate.postForEntity(url, request, responseType);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e); //TODO change exception
        }
    }

}
