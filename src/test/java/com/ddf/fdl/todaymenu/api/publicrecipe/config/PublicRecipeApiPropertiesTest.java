package com.ddf.fdl.todaymenu.api.publicrecipe.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class PublicRecipeApiPropertiesTest {
    
    @Autowired
    PublicRecipeApiProperties properties;

    @Test
    void 공공레시피API설정_조회() {
        log.info("API Key exists: {}", properties.getApiKey() != null && !properties.getApiKey().isBlank());
        log.info("Base URL: {}", properties.getBaseUrl());
    }
}
