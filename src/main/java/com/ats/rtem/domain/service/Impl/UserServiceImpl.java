package com.ats.rtem.domain.service.Impl;

import com.ats.rtem.common.mapper.UserMapper;
import com.ats.rtem.domain.dao.UserDao;
import com.ats.rtem.domain.dto.UserDto;
import com.ats.rtem.domain.entity.User;
import com.ats.rtem.domain.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        log.info("Creating new user: {}", userDto);
        User user = userMapper.mapToUserEntity(userDto);
        User savedUser = userDao.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.mapToUserDto(savedUser);
    }
}
