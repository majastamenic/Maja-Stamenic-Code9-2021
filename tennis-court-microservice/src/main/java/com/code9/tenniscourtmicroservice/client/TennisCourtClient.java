package com.code9.tenniscourtmicroservice.client;

import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface TennisCourtClient {
    @RequestLine("GET /reservation/{id}")
    ResponseEntity getById(@PathVariable Long id);

    @RequestLine("PUT /reservation/paid/{id}")
    ResponseEntity paid(@PathVariable Long id);
}
