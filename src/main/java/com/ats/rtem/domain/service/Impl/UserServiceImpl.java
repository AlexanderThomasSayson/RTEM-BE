package com.ats.rtem.domain.service.Impl;

import com.ats.rtem.common.mapper.UserMapper;
import com.ats.rtem.domain.dao.UserDao;
import com.ats.rtem.domain.dto.UserDto;
import com.ats.rtem.domain.entity.User;
import com.ats.rtem.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDto> searchUsers(String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        log.info("Fetching Users with keyword: {}, startDate: {}, endDate: {}", keyword, startDate, endDate);

        Page<User> users;
        if(keyword == null || keyword.isBlank()){
            users = userDao.searchByDateRange(startDate, endDate,pageable);
        } else {
            users = userDao.searchUsersByKeywordAndDateRange(keyword,startDate,endDate,pageable);
        }
        return users.map(userMapper::mapToDto);
    }
}
