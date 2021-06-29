package com.code9.usermicroservice.user.service.impl;

import com.code9.usermicroservice.jwt.JwtUtil;
import com.code9.usermicroservice.user.domain.User;
import com.code9.usermicroservice.user.service.AuthService;
import com.code9.usermicroservice.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.code9.usermicroservice.exception.BadRequestException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        User user = userService.findByUsername(username);
        if(user == null || !passwordEncoder.matches(password, user.getPassword()))
            throw new BadRequestException("Username or password is incorrect!");
        return jwtUtil.createToken(user.getUsername());
    }
}
