package com.dzydowicz.virtualthreads.application.dto;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeteoriteLandingRequest {
    private Long id;
    private String name;
    private MeteoriteLandingNameTypeEnum nameType;
    private String recClass;
    private Float mass;
    private MeteoriteLandingFallEnum fall;
    private Integer year;
    private Float recLat;
    private Float recLong;
    private String geolocation;
}
