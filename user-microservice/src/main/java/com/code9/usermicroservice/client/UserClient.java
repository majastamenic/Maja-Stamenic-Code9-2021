package com.code9.usermicroservice.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

public interface UserClient {

    @RequestLine("GET /api/{id}")
    ResponseEntity getUser(@Param Long id);

    @RequestLine("GET /api/isAdmin/{email}")
    ResponseEntity checkIsAdmin(@Param String email);
}
