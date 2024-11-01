package com.example.dexters_formation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.dexters_formation.repository")
@EntityScan(basePackages = "com.example.dexters_formation.entity")
public class TestConfig {

    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate();
    }
}