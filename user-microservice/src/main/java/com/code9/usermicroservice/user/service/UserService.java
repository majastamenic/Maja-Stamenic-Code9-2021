package com.code9.usermicroservice.user.service;

import com.code9.usermicroservice.user.domain.Role;
import com.code9.usermicroservice.user.domain.User;
import com.code9.usermicroservice.user.exception.BadRequestException;
import com.code9.usermicroservice.user.exception.NotFoundException;
import com.code9.usermicroservice.user.exception.UnauthorizedException;
import com.code9.usermicroservice.user.repository.IUserRepository;
import com.code9.usermicroservice.user.service.interfaces.IRoleService;
import com.code9.usermicroservice.user.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleService roleService;

    private static String admin = "ADMIN";
    private static String tennisPlayer = "TENNIS_PLAYER";

    public UserService(IUserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new NotFoundException("There is no any user.");
        return users;
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

    public void deleteTennisPlayer(Long id) {
        User user = userRepository.findUserById(id);
        checkTennisPlayer(user.getEmail());
        userRepository.delete(user);
    }

    private User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new NotFoundException("There is no user with email: " + email);
        return user;
    }
}
