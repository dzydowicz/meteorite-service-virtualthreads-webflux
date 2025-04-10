package com.dzydowicz.webflux.infrastructure.web;

import com.dzydowicz.webflux.application.MeteoriteLandingServicePort;
import com.dzydowicz.webflux.application.MeteoriteLandingsCalculationsServicePort;
import com.dzydowicz.webflux.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.webflux.application.dto.MeteoriteLandingKineticEnergyDTO;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/meteorite-landings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class MeteoriteLandingController {
    private final MeteoriteLandingServicePort meteoriteLandingServicePort;
    private final MeteoriteLandingsCalculationsServicePort meteoriteLandingsCalculationsServicePort;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MeteoriteLandingDTO>> getMeteoriteLanding(@PathVariable Long id) {
        return meteoriteLandingServicePort
                .getMeteoriteLanding(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<MeteoriteLandingDTO> listMeteoriteLandings(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            @RequestParam(value = "nameType", required = false) MeteoriteLandingNameTypeEnum nameType,
            @RequestParam(value = "fall", required = false) MeteoriteLandingFallEnum fall,
            @RequestParam(value = "minMass", required = false) Float minMass,
            @RequestParam(value = "maxMass", required = false) Float maxMass,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear) {

        MeteoriteLandingFilterDTO filter = MeteoriteLandingFilterDTO.builder()
                .ids(ids == null ? Collections.emptyList() : ids)
                .nameType(nameType)
                .fall(fall)
                .minMass(minMass)
                .maxMass(maxMass)
                .minYear(minYear)
                .maxYear(maxYear)
                .build();

        return meteoriteLandingServicePort
                .getMeteoriteLandings(filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MeteoriteLandingDTO> createMeteoriteLanding(@RequestBody CreateMeteoriteLandingRequest createMeteoriteLandingRequest) {
        return meteoriteLandingServicePort
                .createMeteoriteLanding(createMeteoriteLandingRequest);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> removeMeteoriteLanding(@PathVariable Long id) {
        return meteoriteLandingServicePort
                .removeMeteoriteLanding(id)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/impact-energy")
    public Mono<ResponseEntity<MeteoriteLandingKineticEnergyDTO>> getMeteoriteLandingImpactEnergy(@PathVariable Long id) {
        return meteoriteLandingsCalculationsServicePort
                .getMeteoriteLandingWithKineticEnergy(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/impact-energy")
    public Flux<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingImpactEnergy(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            @RequestParam(value = "nameType", required = false) MeteoriteLandingNameTypeEnum nameType,
            @RequestParam(value = "fall", required = false) MeteoriteLandingFallEnum fall,
            @RequestParam(value = "minMass", required = false) Float minMass,
            @RequestParam(value = "maxMass", required = false) Float maxMass,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear) {

        MeteoriteLandingFilterDTO filter = MeteoriteLandingFilterDTO.builder()
                .ids(ids == null ? Collections.emptyList() : ids)
                .nameType(nameType)
                .fall(fall)
                .minMass(minMass)
                .maxMass(maxMass)
                .minYear(minYear)
                .maxYear(maxYear)
                .build();

        return meteoriteLandingsCalculationsServicePort
                .getMeteoriteLandingsWithKineticEnergyList(filter);
    }
}
