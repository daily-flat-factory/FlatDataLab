package com.ddf.fdl.todaymenu.api.publicrecipe.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ddf.fdl.todaymenu.api.publicrecipe.repository.RecipeRawRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class RecipeRawIngestServiceTest {
    
    @Autowired
    RecipeRawIngestService recipeRawIngestService;
    @Autowired
    RecipeRawRepository recipeRawRepository;

    @Test
    void 공공레시피API를_호출해서_raw데이터를_저장한다(){
        int savedCount = recipeRawIngestService.ingestRecipes(1, 10);
        log.info("Saved recipes: {}", savedCount);
        assertThat(savedCount).isGreaterThanOrEqualTo(0);
        assertThat(recipeRawRepository.count()).isGreaterThanOrEqualTo(savedCount);
    }

}
