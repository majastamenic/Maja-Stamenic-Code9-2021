package com.code9.usermicroservice.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-client")
public interface UserClient {

    @RequestLine("GET /api/{id}")
    ResponseEntity getUser(@Param Long id);

    @RequestLine("GET /api/isAdmin/{username}")
    ResponseEntity checkIsAdmin(@Param String username);
}
