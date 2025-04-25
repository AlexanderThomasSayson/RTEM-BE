package com.ats.rtem.config.init;

import com.ats.rtem.domain.dao.RoleDao;
import com.ats.rtem.domain.entity.Role;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoleInitializer {

    private  final RoleDao roleDao;

    public RoleInitializer(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PostConstruct
    @Transactional
    public void initializeRoles(){
        createRoleIfNotExist("USER");
        createRoleIfNotExist("ADMIN");
        createRoleIfNotExist("SUPER_ADMIN");
    }

    private void createRoleIfNotExist(String roleName){
        if(roleDao.findByRoleName(roleName) == null){
            Role role = new Role();
            role.setRoleName(roleName);
            role.setIsActive(true);
            roleDao.save(role);
            log.info("Role '{}' created", roleName);
        }else{
            log.info("Role '{}' already exist", roleName);
        }
    }

}
