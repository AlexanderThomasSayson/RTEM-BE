package com.ats.rtem.domain.dao;

import com.ats.rtem.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
