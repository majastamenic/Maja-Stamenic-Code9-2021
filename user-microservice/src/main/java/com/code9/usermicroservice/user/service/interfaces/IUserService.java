package com.code9.usermicroservice.user.service.interfaces;

import com.code9.usermicroservice.user.controller.dto.LoginDto;
import com.code9.usermicroservice.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User createTennisPlayer(User user);

    User updateTennisPlayer(User user);

    void checkAdmin(String email);

    void checkTennisPlayer(String email);

    User deleteTennisPlayer(Long id);

    User findByUsername(String username);

//    String login(String username, String password);
}
