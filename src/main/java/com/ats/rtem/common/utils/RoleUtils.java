package com.ats.rtem.common.utils;

import com.ats.rtem.common.constants.ErrorMessages;
import com.ats.rtem.domain.dao.RoleDao;
import com.ats.rtem.domain.entity.Role;
import com.ats.rtem.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoleUtils {

    private final RoleDao roleDao;

    public RoleUtils(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findRoleById(Long id){
        return roleDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Role with ID: {} not found", id);
                    return new ResourceNotFoundException(String.format(ErrorMessages.ROLE_NOT_FOUND,id));
                });
    }
}
