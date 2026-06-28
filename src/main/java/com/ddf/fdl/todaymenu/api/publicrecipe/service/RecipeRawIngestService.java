package com.ddf.fdl.todaymenu.api.publicrecipe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddf.fdl.todaymenu.api.publicrecipe.client.PublicRecipeApiClient;
import com.ddf.fdl.todaymenu.api.publicrecipe.entity.RecipeRaw;
import com.ddf.fdl.todaymenu.api.publicrecipe.repository.RecipeRawRepository;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class RecipeRawIngestService {

    private final PublicRecipeApiClient publicRecipeApiClient;
    private final RecipeRawRepository recipeRawRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public int ingestRecipes(int start, int end) {
        String response = publicRecipeApiClient.fetchRecipes(start, end);

        if (response == null || response.isBlank()) {
            throw new RuntimeException("공공 레시피 API 응답이 비어 있습니다.");
        }

        try{
            JsonNode root = objectMapper.readTree(response);
            JsonNode cookRcp = root.path("COOKRCP01");
            JsonNode rows = cookRcp.path("row");

            if (!rows.isArray()){
                JsonNode result = cookRcp.path("RESULT");
                String message = result.path("MSG").asText("알 수 없는 API 응답 형식입니다.");
                throw new IllegalStateException("공공 레시피 API 응답 형식이 예상과 다릅니다. 메시지: " + message);
            }
        
            int savedCount = 0;

            for (JsonNode row : rows) {
                String rcpSeq = row.path("RCP_SEQ").asText();
                if (rcpSeq == null || rcpSeq.isBlank()) {
                    continue; // RCP_SEQ가 없으면 저장하지 않음
                }

                if (recipeRawRepository.existsByRcpSeq(rcpSeq)) {
                    continue; // 이미 존재하면 저장하지 않음
                }

                String rawJson = objectMapper.writeValueAsString(row);
                RecipeRaw recipeRaw = new RecipeRaw(rcpSeq, rawJson);

                recipeRawRepository.save(recipeRaw);
                savedCount++;
            }

            return savedCount;
        } catch (Exception e) {
            throw new RuntimeException("레시피 저장 중 오류가 발생했습니다.", e);
        }
    }

}
