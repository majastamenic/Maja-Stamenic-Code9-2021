package com.code9.paymicroservice;

import com.code9.tenniscourtmicroservice.client.TennisCourtClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class PayMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayMicroserviceApplication.class, args);
    }

    @Bean
    public TennisCourtClient tennisCourtClientClient() {
        return Feign
                .builder()
                .decoder(new GsonDecoder())
                .target(TennisCourtClient.class, "http://localhost:9099");
    }

    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.code9"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
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
}
