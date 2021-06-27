package com.code9.tenniscourtmicroservice.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-microservice", url = "http://localhost:9090")
public interface IUserCommunication {

    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable Long id);

    @GetMapping("/isAdmin/{email}")
    ResponseEntity checkIsAdmin(@PathVariable String email);
}
