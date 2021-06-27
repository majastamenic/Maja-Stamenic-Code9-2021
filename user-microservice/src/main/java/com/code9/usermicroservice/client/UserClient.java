package com.code9.usermicroservice.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

public interface UserClient {

    @RequestLine("GET /{id}")
    ResponseEntity getUser(@Param Long id);

    @RequestLine("GET /isAdmin/{email}")
    ResponseEntity checkIsAdmin(@Param String email);
}
