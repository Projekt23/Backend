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
                .pathsToMatch("/*")
                .build();
    }
    /**
     * User Api Dokumentations- und Test-Seite
     * @return OpenApi Object
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/api/user/*")
                .build();
    }
    @Bean
    public GroupedOpenApi AuthApi() {
        return GroupedOpenApi.builder()
                .group("auth-api")
                .pathsToMatch("/api/auth/*")
                .build();
    }
    /**
     * BusinessObject Api Dokumentations- und Test-Seite
     * @return OpenApi BusinessObject
     */
    @Bean
    public GroupedOpenApi businessObjectApi() {
        return GroupedOpenApi.builder()
                .group("businessobject-api")
                .pathsToMatch("/api/businessobject/*")
                .build();
    }
}
