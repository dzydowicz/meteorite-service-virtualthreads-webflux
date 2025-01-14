package com.dzydowicz.virtualthreads.infrastructure.calculations;

import com.dzydowicz.virtualthreads.application.CalculationsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
class CalculationsServiceClientConfiguration {

    private final Environment env;

    @Bean
    CalculationsServicePort calculationsServicePort() {
        RestTemplate restTemplate = new RestTemplate();
        return new CalculationsServiceRestClient(restTemplate, env.getProperty("calculations-service.api.url"));
    }

}
