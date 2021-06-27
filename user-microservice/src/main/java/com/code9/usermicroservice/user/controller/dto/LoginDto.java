package com.code9.usermicroservice.user.controller.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
