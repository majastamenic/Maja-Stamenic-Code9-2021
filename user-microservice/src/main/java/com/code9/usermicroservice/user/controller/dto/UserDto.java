package com.code9.usermicroservice.user.controller.dto;

import com.code9.usermicroservice.user.domain.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<Role> roles;
}
