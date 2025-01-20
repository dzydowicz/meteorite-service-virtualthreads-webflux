package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingNameTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="meteorite_landings")
@AllArgsConstructor
@NoArgsConstructor
@Data
class MeteoriteLandingEntity {
    @Id
    private Long id;

    private String name;

    @Column("nametype")
    private MeteoriteLandingNameTypeEnum nameType;

    @Column("recclass")
    private String recClass;

    private Float mass;

    private MeteoriteLandingFallEnum fall;

    private Integer year;

    @Column("reclat")
    private Float recLat;

    @Column("reclong")
    private Float recLong;

    private String geolocation;
}
