package com.code9.usermicroservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@ConfigurationProperties("tennis-scheduler.api")
@ConditionalOnProperty(name = "tennis-scheduler.api.swagger.enable", havingValue = "true")
@Getter
@Setter
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.code9"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Address book API",
                "User microservice for Tennis scheduler",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Maja Stamenic", "https://majastamenic.herokuapp.com", "stamenicmaja5@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }


}