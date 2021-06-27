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
}
