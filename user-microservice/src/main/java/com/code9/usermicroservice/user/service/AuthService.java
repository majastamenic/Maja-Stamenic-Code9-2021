package com.code9.usermicroservice.user.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(String username, String password);
}
