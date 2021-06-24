package com.code9.usermicroservice.user.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
