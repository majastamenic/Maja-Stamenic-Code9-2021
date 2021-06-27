package com.code9.usermicroservice.user.service.impl;

import com.code9.usermicroservice.exception.BadRequestException;
import com.code9.usermicroservice.exception.NotFoundException;
import com.code9.usermicroservice.exception.UnauthorizedException;
import com.code9.usermicroservice.user.domain.Role;
import com.code9.usermicroservice.user.domain.User;
import com.code9.usermicroservice.user.repository.UserRepository;
import com.code9.usermicroservice.user.service.RoleService;
import com.code9.usermicroservice.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    //private PasswordEncoder passwordEncoder;
    //private JwtService jwtService;

    private static String admin = "ADMIN";
    private static String tennisPlayer = "TENNIS_PLAYER";


    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new NotFoundException("There is no any user.");
        return users.stream().filter(user -> !(Boolean.TRUE.equals(user.getDeleted()))).collect(Collectors.toList());
    }

    public User createTennisPlayer(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName(tennisPlayer));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User updateTennisPlayer(User user) {
        User dbUser = findUserByEmail(user.getEmail());
        checkTennisPlayer(dbUser.getEmail());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setDateOfBirth(user.getDateOfBirth());
        return userRepository.save(dbUser);
    }

    public void checkAdmin(String email) {
        User user = findUserByEmail(email);
        Role role = roleService.findByName(admin);
        if (!(user.getRoles().contains(role)))
            throw new UnauthorizedException("Access denied due to invalid role.");
    }

    public void checkTennisPlayer(String email) {
        Role role = roleService.findByName(tennisPlayer);
        User user = findUserByEmail(email);
        if (!(user.getRoles().contains(role)))
            throw new BadRequestException("User with email: " + email + " is not tennis player.");
    }

    public User deleteTennisPlayer(Long id) {
        User user = userRepository.findUserById(id);
        checkTennisPlayer(user.getEmail());
        user.setDeleted(true);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new NotFoundException("There is no user with username: " + username);
        return user;
    }

//    @Override
//    public String login(String username, String password) {
//        User user = userRepository.findUserByUsername(username);
//        if(user == null || !passwordEncoder.matches(password, user.getPassword()))
//            throw new BadRequestException("Bad login data");
//        return jwtService.createToken(user.getUsername());
//    }

    private User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new NotFoundException("There is no user with email: " + email);
        return user;
    }
}
