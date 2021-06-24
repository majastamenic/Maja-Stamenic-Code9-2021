package com.code9.usermicroservice.user.service.interfaces;

import com.code9.usermicroservice.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User createTennisPlayer(User user);

    User updateTennisPlayer(User user);

    void checkAdmin(String email);

    void deleteTennisPlayer(Long id);
}
