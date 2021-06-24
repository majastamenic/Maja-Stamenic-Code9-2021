package com.code9.usermicroservice.user.controller.mapping;

import com.code9.usermicroservice.user.controller.dto.UserDto;
import com.code9.usermicroservice.user.domain.User;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        return user;
    }
}
