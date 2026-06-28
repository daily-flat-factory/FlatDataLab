package com.ddf.fdl.todaymenu.api.publicrecipe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 지정된 prefix 뒤에 붙은 이름과 클래스의 필드명 매칭
@ConfigurationProperties(prefix = "today-menu.public-recipe")
public class PublicRecipeApiProperties {
    
    private final String apiKey;
    private final String baseUrl;

}
