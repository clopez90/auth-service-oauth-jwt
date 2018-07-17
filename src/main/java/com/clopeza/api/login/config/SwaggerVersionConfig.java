package com.clopeza.api.login.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerVersionConfig {

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "Header");
    }

    @Bean
    public Docket apiSwaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clopeza.api.login.controller"))
                .paths(configPathV1())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title("Agora API")
                        .description("Documentation Agora API v1")
                        .build())
                .securitySchemes(Collections.singletonList(apiKey()));
    }


    private Predicate<String> configPathV1() {
        return or(
                PathSelectors.regex("/api/users/v1.*")
        );
    }


}
