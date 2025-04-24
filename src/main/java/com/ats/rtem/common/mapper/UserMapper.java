package com.ats.rtem.common.mapper;

import com.ats.rtem.domain.dto.UserDto;
import com.ats.rtem.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUserEntity(UserDto userDto){
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatusId(userDto.getStatusId());
        user.setDateCreated(userDto.getDateCreated());
        user.setDateModified(userDto.getDateModified());

        return user;
    }

    public UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setStatusId(user.getStatusId());
        userDto.setDateCreated(user.getDateCreated());
        userDto.setDateModified(user.getDateModified());

        return userDto;
    }
}
