package com.code9.usermicroservice.user.service;

import com.code9.usermicroservice.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll();

    User createTennisPlayer(User user);

    User updateTennisPlayer(User user);

    void checkAdmin(String email);

    void checkTennisPlayer(String email);

    User deleteTennisPlayer(Long id);

    User findByUsername(String username);

    User findById(Long id);

//    String login(String username, String password);
}
