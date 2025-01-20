package com.dzydowicz.webflux.infrastructure.calculations;

import com.dzydowicz.webflux.application.CalculationsServicePort;
import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyRequest;
import com.dzydowicz.webflux.application.dto.calculations.CalculateKineticEnergyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
class CalculationsServiceRestClient implements CalculationsServicePort {

    private final WebClient webClient;
    private final String calculationsServiceUrl;

    @Override
    public Mono<CalculateKineticEnergyResponse> calculateKineticEnergy(CalculateKineticEnergyRequest request) {
        return performPostApiCall(
                calculationsServiceUrl + "/calculate-kinetic-energy",
                request,
                CalculateKineticEnergyResponse.class
        );
    }

    private <R, T> Mono<R> performPostApiCall(String url, T request, Class<R> responseType) {
        return webClient.post()
                .uri(url)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(e -> new CalculationsServiceRestClientException("Failed to call Calculations Service API: " + e.getMessage()));
    }
}
