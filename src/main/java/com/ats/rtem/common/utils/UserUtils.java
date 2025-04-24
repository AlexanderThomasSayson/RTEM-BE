package com.ats.rtem.common.utils;

import com.ats.rtem.common.constants.ErrorMessages;
import com.ats.rtem.domain.dao.UserDao;
import com.ats.rtem.domain.entity.User;
import com.ats.rtem.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserUtils {

    private final UserDao userDao;

    public UserUtils(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Finds a User by ID, throws an exception if not found.
     */
    public User findUserById(Long id){
        return userDao.findById(id)
                .orElseThrow(()-> {
                    log.error("User with ID: {} not found", id);
                    return new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND,id));
                });
    }


}
