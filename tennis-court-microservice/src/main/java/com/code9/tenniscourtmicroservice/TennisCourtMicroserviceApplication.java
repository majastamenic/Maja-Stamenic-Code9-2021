package com.code9.tenniscourtmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TennisCourtMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtMicroserviceApplication.class, args);
	}

}
