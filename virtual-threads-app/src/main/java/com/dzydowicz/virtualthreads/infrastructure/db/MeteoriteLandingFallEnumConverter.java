package com.dzydowicz.virtualthreads.infrastructure.db;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFallEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class MeteoriteLandingFallEnumConverter implements AttributeConverter<MeteoriteLandingFallEnum, String> {

    @Override
    public String convertToDatabaseColumn(MeteoriteLandingFallEnum attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public MeteoriteLandingFallEnum convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty() ? null : MeteoriteLandingFallEnum.fromLabel(dbData);
    }
}
