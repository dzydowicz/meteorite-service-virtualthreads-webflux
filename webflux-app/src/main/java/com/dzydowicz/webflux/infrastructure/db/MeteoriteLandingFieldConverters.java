package com.dzydowicz.webflux.infrastructure.db;

import com.dzydowicz.webflux.domain.model.MeteoriteLandingFallEnum;
import com.dzydowicz.webflux.domain.model.MeteoriteLandingNameTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

class MeteoriteLandingFieldConverters {

    /**
     * Reading Converter: String -> MeteoriteLandingFallEnum
     */
    @ReadingConverter
    public static class MeteoriteLandingFallEnumReadingConverter implements Converter<String, MeteoriteLandingFallEnum> {
        @Override
        public MeteoriteLandingFallEnum convert(String source) {
            return source == null || source.isEmpty() ? null : MeteoriteLandingFallEnum.fromLabel(source);
        }
    }

    /**
     * Writing Converter: MeteoriteLandingFallEnum -> String
     */
    @WritingConverter
    public static class MeteoriteLandingFallEnumWritingConverter implements Converter<MeteoriteLandingFallEnum, String> {
        @Override
        public String convert(MeteoriteLandingFallEnum source) {
            return source == null ? null : source.getLabel();
        }
    }

    /**
     * Reading Converter: String -> MeteoriteLandingNameTypeEnum
     */
    @ReadingConverter
    public static class MeteoriteLandingNameTypeEnumReadingConverter implements Converter<String, MeteoriteLandingNameTypeEnum> {
        @Override
        public MeteoriteLandingNameTypeEnum convert(String source) {
            return source == null || source.isEmpty() ? null : MeteoriteLandingNameTypeEnum.fromLabel(source);
        }
    }

    /**
     * Writing Converter: MeteoriteLandingNameTypeEnum -> String
     */
    @WritingConverter
    public static class MeteoriteLandingNameTypeEnumWritingConverter implements Converter<MeteoriteLandingNameTypeEnum, String> {
        @Override
        public String convert(MeteoriteLandingNameTypeEnum source) {
            return source == null ? null : source.getLabel();
        }
    }
}
