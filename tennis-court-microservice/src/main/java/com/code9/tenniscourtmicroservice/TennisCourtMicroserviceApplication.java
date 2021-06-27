package com.code9.tenniscourtmicroservice;

import com.code9.usermicroservice.client.UserClient;;
import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import feign.gson.GsonDecoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TennisCourtMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisCourtMicroserviceApplication.class, args);
    }

    @Bean
    public UserClient userClient() {
        return Feign
                .builder()
                .decoder(new GsonDecoder())
                .target(UserClient.class, "http://localhost:9090");
    }

}
