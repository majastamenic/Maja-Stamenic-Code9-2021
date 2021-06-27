package com.code9.usermicroservice.user.controller;

import com.code9.usermicroservice.user.controller.dto.UserDto;
import com.code9.usermicroservice.user.controller.mapping.UserMapper;
import com.code9.usermicroservice.user.domain.User;
import com.code9.usermicroservice.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Api(value = "User endpoints")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Get all tennis players", notes = "", response = ResponseEntity.class)
    public ResponseEntity getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create tennis player", notes = "", response = User.class)
    public ResponseEntity createTennisPlayer(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity(
                userService.createTennisPlayer(UserMapper.mapUserDtoToUser(userDto)), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{email}")
    @ApiOperation(value = "Update tennis player", notes = "", response = User.class)
    public ResponseEntity updateTennisPlayer(@RequestBody UserDto userDto, @PathVariable String email) {
        userService.checkAdmin(email);
        return new ResponseEntity(
                userService.updateTennisPlayer(UserMapper.mapUserDtoToUser(userDto)), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/{email}")
    @ApiOperation(value = "Delete tennis player", notes = "Delete tennis plyer by id", response = User.class)
    public ResponseEntity deleteTennisPlayer(@PathVariable Long id, @PathVariable String email) {
        userService.checkAdmin(email);
        return new ResponseEntity(userService.deleteTennisPlayer(id), HttpStatus.OK);
    }

    @GetMapping("/isAdmin/{email}")
    @ApiOperation(value = "Chech is user admin", notes = "", response = User.class)
    public ResponseEntity checkIsAdmin(@PathVariable String email) {
        userService.checkAdmin(email);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginDto loginDto){
//        return new ResponseEntity(userService.login(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
//    }
}
