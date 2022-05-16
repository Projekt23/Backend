package com.project23.app.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    /**
     * Default Api Dokumentations- und Test-Seite
     * @return OpenApi Object
     */
    @Bean
    public GroupedOpenApi Api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**")
                .build();
    }
    /**
     * Label Api Dokumentations- und Test-Seite
     * @return OpenApi Label
     */
    @Bean
    public GroupedOpenApi labelApi() {
        return GroupedOpenApi.builder()
                .group("label-api")
                .pathsToMatch("/api/label/*")
                .build();
    }
    /**
     * Label Api Dokumentations- und Test-Seite
     * @return OpenApi Favourite
     */
    @Bean
    public GroupedOpenApi favouriteApi() {
        return GroupedOpenApi.builder()
                .group("favourite-api")
                .pathsToMatch("/api/favourite/*")
                .build();
    }
}
