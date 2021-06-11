package com.code9.usermicroservice.admin.domain;

import com.code9.usermicroservice.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
}
