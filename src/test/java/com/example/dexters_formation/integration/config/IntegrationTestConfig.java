package com.example.dexters_formation.integration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class IntegrationTestConfig {
    
    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }
} 