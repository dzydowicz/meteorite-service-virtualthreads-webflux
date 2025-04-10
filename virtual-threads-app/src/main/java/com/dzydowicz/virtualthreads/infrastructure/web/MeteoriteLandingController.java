package com.dzydowicz.virtualthreads.infrastructure.web;

import com.dzydowicz.virtualthreads.application.MeteoriteLandingServicePort;
import com.dzydowicz.virtualthreads.application.MeteoriteLandingsCalculationsServicePort;
import com.dzydowicz.virtualthreads.application.dto.CreateMeteoriteLandingRequest;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingFilterDTO;
import com.dzydowicz.virtualthreads.application.dto.MeteoriteLandingKineticEnergyDTO;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/meteorite-landings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class MeteoriteLandingController {

    private final MeteoriteLandingServicePort meteoriteLandingServicePort;
    private final MeteoriteLandingsCalculationsServicePort meteoriteLandingsCalculationsServicePort;

    @GetMapping("/{id}")
    public ResponseEntity<MeteoriteLandingDTO> getMeteoriteLanding(@PathVariable Long id) {
        MeteoriteLandingDTO meteoriteLanding = meteoriteLandingServicePort.getMeteoriteLanding(id);
        return new ResponseEntity<>(meteoriteLanding, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MeteoriteLandingDTO>> listMeteoriteLandings(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            @RequestParam(value = "nameType", required = false) MeteoriteLandingNameTypeEnum nameType,
            @RequestParam(value = "fall", required = false) MeteoriteLandingFallEnum fall,
            @RequestParam(value = "minMass", required = false) Float minMass,
            @RequestParam(value = "maxMass", required = false) Float maxMass,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear) {

        MeteoriteLandingFilterDTO filter = MeteoriteLandingFilterDTO.builder()
                .ids(ids)
                .nameType(nameType)
                .fall(fall)
                .minMass(minMass)
                .maxMass(maxMass)
                .minYear(minYear)
                .maxYear(maxYear)
                .build();

        List<MeteoriteLandingDTO> filteredLandings = meteoriteLandingServicePort.getMeteoriteLandings(filter);
        return new ResponseEntity<>(filteredLandings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MeteoriteLandingDTO> createMeteoriteLanding(@RequestBody CreateMeteoriteLandingRequest createMeteoriteLandingRequest) {
        MeteoriteLandingDTO meteoriteLanding = meteoriteLandingServicePort.createMeteoriteLanding(createMeteoriteLandingRequest);
        return new ResponseEntity<>(meteoriteLanding, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMeteoriteLanding(@PathVariable Long id) {
        meteoriteLandingServicePort.removeMeteoriteLanding(id);
        return new ResponseEntity<>("Meteorite landing with id " + id + "correctly removed.", HttpStatus.OK);
    }

    @PostMapping("/{id}/impact-energy")
    public ResponseEntity<MeteoriteLandingKineticEnergyDTO> getMeteoriteLandingImpactEnergy(@PathVariable Long id) {
        MeteoriteLandingKineticEnergyDTO meteoriteLandingWithKineticEnergy =
                meteoriteLandingsCalculationsServicePort.getMeteoriteLandingWithKineticEnergy(id);

        return new ResponseEntity<>(meteoriteLandingWithKineticEnergy, HttpStatus.OK);
    }

    @PostMapping("/impact-energy")
    public ResponseEntity<List<MeteoriteLandingKineticEnergyDTO>> getMeteoriteLandingImpactEnergy(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            @RequestParam(value = "nameType", required = false) MeteoriteLandingNameTypeEnum nameType,
            @RequestParam(value = "fall", required = false) MeteoriteLandingFallEnum fall,
            @RequestParam(value = "minMass", required = false) Float minMass,
            @RequestParam(value = "maxMass", required = false) Float maxMass,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear) {

        MeteoriteLandingFilterDTO filter = MeteoriteLandingFilterDTO.builder()
                .ids(ids)
                .nameType(nameType)
                .fall(fall)
                .minMass(minMass)
                .maxMass(maxMass)
                .minYear(minYear)
                .maxYear(maxYear)
                .build();

        List<MeteoriteLandingKineticEnergyDTO> meteoriteLandingWithKineticEnergy =
                meteoriteLandingsCalculationsServicePort.getMeteoriteLandingsWithKineticEnergyList(filter);

        return new ResponseEntity<>(meteoriteLandingWithKineticEnergy, HttpStatus.OK);
    }
}
