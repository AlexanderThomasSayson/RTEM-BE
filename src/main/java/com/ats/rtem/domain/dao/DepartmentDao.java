package com.ats.rtem.domain.dao;

import com.ats.rtem.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {
    Department findByDepartmentName(String departmentName);
}
