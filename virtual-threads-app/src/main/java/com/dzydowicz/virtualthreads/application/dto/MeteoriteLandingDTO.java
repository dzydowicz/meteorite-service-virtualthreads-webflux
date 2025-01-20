package com.dzydowicz.virtualthreads.application.dto;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLanding;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeteoriteLandingDTO {
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

    public static MeteoriteLandingDTO from(MeteoriteLanding meteoriteLanding) {
        return new MeteoriteLandingDTO(
                meteoriteLanding.id(),
                meteoriteLanding.name(),
                meteoriteLanding.nameType(),
                meteoriteLanding.recClass(),
                meteoriteLanding.mass(),
                meteoriteLanding.fall(),
                meteoriteLanding.year(),
                meteoriteLanding.recLat(),
                meteoriteLanding.recLong(),
                meteoriteLanding.geolocation()
        );
    }
}
