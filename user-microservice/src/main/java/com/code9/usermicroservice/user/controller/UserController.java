package com.code9.usermicroservice.user.controller;

import com.code9.usermicroservice.user.controller.dto.UserDto;
import com.code9.usermicroservice.user.controller.mapping.UserMapper;
import com.code9.usermicroservice.user.service.UserService;
import com.code9.usermicroservice.user.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createTennisPlayer(@RequestBody UserDto userDto) {
        return new ResponseEntity(
                userService.createTennisPlayer(UserMapper.mapUserDtoToUser(userDto)), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity updateTennisPlayer(@RequestBody UserDto userDto, @PathVariable String email) {
        userService.checkAdmin(email);
        return new ResponseEntity(
                userService.updateTennisPlayer(UserMapper.mapUserDtoToUser(userDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{email}")
    public ResponseEntity deleteTennisPlayer(@PathVariable Long id, @PathVariable String email) {
        userService.checkAdmin(email);
        userService.deleteTennisPlayer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
