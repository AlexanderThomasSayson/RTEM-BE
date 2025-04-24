package com.ats.rtem.config.init;

import com.ats.rtem.domain.dao.DepartmentDao;
import com.ats.rtem.domain.entity.Department;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepartmentInitializer {
    private final DepartmentDao departmentDao;

    public DepartmentInitializer(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @PostConstruct
    @Transactional
    public void initializeDepartment(){
        createDepartmentIfNotExist("Developers");
        createDepartmentIfNotExist("Management");
        createDepartmentIfNotExist("Utilities");
    }

    private void createDepartmentIfNotExist(String departmentName){
        if(departmentDao.findByDepartmentName(departmentName) == null){
            Department department = new Department();
            department.setDepartmentName(departmentName);
            departmentDao.save(department);
            log.info("Department '{}' created", departmentName);
        }else{
            log.info("Department '{}' already exist", departmentName);
        }
    }
}
