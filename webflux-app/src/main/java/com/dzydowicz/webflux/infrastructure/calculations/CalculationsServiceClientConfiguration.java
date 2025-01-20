package com.dzydowicz.webflux.infrastructure.calculations;

import com.dzydowicz.webflux.application.CalculationsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
class CalculationsServiceClientConfiguration {

    private final Environment env;

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    CalculationsServicePort calculationsServicePort(WebClient webClient) {
        return new CalculationsServiceRestClient(webClient, env.getProperty("calculations.service.url"));
    }
}
