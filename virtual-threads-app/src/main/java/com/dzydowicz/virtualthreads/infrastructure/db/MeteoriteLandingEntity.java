package com.dzydowicz.virtualthreads.infrastructure.db;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingNameTypeEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="meteorite_landings")
@AllArgsConstructor
@NoArgsConstructor
@Data
class MeteoriteLandingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "nametype")
    @Convert(converter = MeteoriteLandingNameTypeEnumConverter.class)
    private MeteoriteLandingNameTypeEnum nameType;

    @Column(name = "recclass", length = 100)
    private String recClass;

    private Float mass;

    @Convert(converter = MeteoriteLandingFallEnumConverter.class)
    private MeteoriteLandingFallEnum fall;

    private Integer year;

    @Column(name = "reclat")
    private Float recLat;

    @Column(name = "reclong")
    private Float recLong;

    @Column(length = 255)
    private String geolocation;
}
