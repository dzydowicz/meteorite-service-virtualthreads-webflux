package com.dzydowicz.virtualthreads.infrastructure.db;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingNameTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class MeteoriteLandingNameTypeEnumConverter implements AttributeConverter<MeteoriteLandingNameTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(MeteoriteLandingNameTypeEnum attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public MeteoriteLandingNameTypeEnum convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty() ? null : MeteoriteLandingNameTypeEnum.fromLabel(dbData);
    }
}
