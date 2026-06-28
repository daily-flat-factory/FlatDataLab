package com.ddf.fdl.todaymenu.api.publicrecipe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ddf.fdl.todaymenu.api.publicrecipe.entity.RecipeRaw;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@Slf4j
public class RecipeRawRepositoryTest {
    
    @Autowired
    RecipeRawRepository recipeRawRepository;

    @Test
    void recipe_raw에_원본_JSON을_저장한다() {
        String rcpSeq = "TEST_001";
        String rawJson = """
                {
                  "RCP_SEQ": "TEST_001",
                  "RCP_NM": "테스트 레시피",
                  "RCP_PARTS_DTLS": "두부 100g, 달걀 1개"
                }
                """;

        RecipeRaw recipeRaw = new RecipeRaw(rcpSeq, rawJson);

        RecipeRaw saved = recipeRawRepository.save(recipeRaw);

        assertThat(saved.getId()).isNotNull();        
        log.info("Saved RecipeRaw ID: {}", saved.getId());
        assertThat(saved.getRcpSeq()).isEqualTo("TEST_001");
        log.info("Saved RecipeRaw RCP_SEQ: {}", saved.getRcpSeq());
        assertThat(saved.getRawJson()).contains("테스트 레시피");
        log.info("Saved RecipeRaw RAW_JSON: {}", saved.getRawJson());
        assertThat(recipeRawRepository.existsByRcpSeq("TEST_001")).isTrue();
    }
}
