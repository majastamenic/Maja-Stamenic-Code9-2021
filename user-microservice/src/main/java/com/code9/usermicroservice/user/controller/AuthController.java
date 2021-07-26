package com.code9.usermicroservice.user.controller;

import com.code9.usermicroservice.user.controller.dto.LoginDto;
import com.code9.usermicroservice.user.service.AuthService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Api(value = "Authentification endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity(authService.login(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
    }
}
