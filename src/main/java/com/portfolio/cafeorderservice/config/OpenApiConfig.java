package com.portfolio.cafeorderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cafeOrderOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cafe Order Service API")
                        .description("Java Spring Boot 기반 카페 주문 관리 포트폴리오 API")
                        .version("1.0.0"));
    }
}