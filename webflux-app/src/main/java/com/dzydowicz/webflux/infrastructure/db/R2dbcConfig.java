package com.dzydowicz.webflux.infrastructure.db;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.util.ArrayList;
import java.util.List;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Value("${DB_HOST}")
    private String dbHost;

    @Value("${DB_PORT}")
    private int dbPort;

    @Value("${DB_NAME}")
    private String dbName;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "postgres")
                .option(HOST, dbHost)
                .option(PORT, dbPort)
                .option(USER, dbUsername)
                .option(PASSWORD, dbPassword)
                .option(DATABASE, dbName)
                .build();

        return ConnectionFactories.get(options);
    }

    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(getStoreConversions(), getCustomConverters());
    }

    @Override
    protected List<Object> getCustomConverters() {
        List<Object> converters = new ArrayList<>();

        converters.add(new MeteoriteLandingFieldConverters.MeteoriteLandingFallEnumReadingConverter());
        converters.add(new MeteoriteLandingFieldConverters.MeteoriteLandingFallEnumWritingConverter());
        converters.add(new MeteoriteLandingFieldConverters.MeteoriteLandingNameTypeEnumReadingConverter());
        converters.add(new MeteoriteLandingFieldConverters.MeteoriteLandingNameTypeEnumWritingConverter());

        return converters;
    }
}
