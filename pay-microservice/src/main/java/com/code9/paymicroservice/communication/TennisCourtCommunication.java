package com.code9.paymicroservice.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "tennis-court-microservice", url = "http://localhost:9099")
public interface TennisCourtCommunication {

    @GetMapping("/reservation/{id}")
    ResponseEntity getById(@PathVariable Long id);

    @PutMapping("/reservation/paid/{id}")
    ResponseEntity paid(@PathVariable Long id);
}
