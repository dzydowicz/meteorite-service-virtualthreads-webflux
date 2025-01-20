package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLanding;

class MeteoriteLandingEntityConverter {

    public static MeteoriteLanding toDomain(MeteoriteLandingEntity entity) {
        MeteoriteLanding result = null;
        if (entity != null) {
            result = new MeteoriteLanding(
                    entity.getId(),
                    entity.getName(),
                    entity.getNameType(),
                    entity.getRecClass(),
                    entity.getMass(),
                    entity.getFall(),
                    entity.getYear(),
                    entity.getRecLat(),
                    entity.getRecLong(),
                    entity.getGeolocation()
            );
        }

        return result;
    }

    public static MeteoriteLandingEntity toEntity(MeteoriteLanding meteoriteLanding) {
        MeteoriteLandingEntity result = null;
        if (meteoriteLanding != null) {
            result = new MeteoriteLandingEntity();
            result.setId(meteoriteLanding.id());
            result.setName(meteoriteLanding.name());
            result.setNameType(meteoriteLanding.nameType());
            result.setRecClass(meteoriteLanding.recClass());
            result.setMass(meteoriteLanding.mass());
            result.setFall(meteoriteLanding.fall());
            result.setYear(meteoriteLanding.year());
            result.setRecLat(meteoriteLanding.recLat());
            result.setRecLong(meteoriteLanding.recLong());
            result.setGeolocation(meteoriteLanding.geolocation());
        }

        return result;
    }
}
