package com.ddf.fdl.todaymenu.api.publicrecipe.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ddf.fdl.todaymenu.api.publicrecipe.config.PublicRecipeApiProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PublicRecipeApiClient {
    
    private final PublicRecipeApiProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchRecipes(int start, int end) {
    // http://openapi.foodsafetykorea.go.kr/api/인증키/서비스명/요청파일타입/요청시작위치/요청종료위치
        String url = "%s/%s/COOKRCP01/json/%d/%d".formatted(
                    properties.getBaseUrl(),
                    properties.getApiKey(),
                    start,
                    end
            );
        return restTemplate.getForObject(url, String.class);
    }
}
