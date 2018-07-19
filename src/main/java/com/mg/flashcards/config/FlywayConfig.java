package com.mg.flashcards.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    @Profile("dev")
    public Flyway flyway(DataSource dataSource){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("classpath:db/migration");
        flyway.migrate();
        return flyway;
    }

}
