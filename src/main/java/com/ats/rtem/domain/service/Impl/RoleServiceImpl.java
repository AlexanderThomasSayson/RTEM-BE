package com.ats.rtem.domain.service.Impl;

import com.ats.rtem.common.mapper.RoleMapper;
import com.ats.rtem.common.utils.RoleUtils;
import com.ats.rtem.domain.dao.RoleDao;
import com.ats.rtem.domain.dto.RoleDto;
import com.ats.rtem.domain.entity.Role;
import com.ats.rtem.domain.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final RoleMapper roleMapper;
    private final RoleUtils roleUtils;

    public RoleServiceImpl(RoleDao roleDao, RoleMapper roleMapper, RoleUtils roleUtils) {
        this.roleDao = roleDao;
        this.roleMapper = roleMapper;
        this.roleUtils = roleUtils;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        log.info("Creating new Role: {}", roleDto.getRoleName());
        Role role = roleMapper.mapToEntity(roleDto);
        Role savedRole = roleDao.save(role);
        log.info("Role created successfully with ID: {}", roleDto.getId());
        return roleMapper.mapToDto(savedRole);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        log.info("Fetching Role with ID: {}", id);
        Role role = roleUtils.findRoleById(id);
        return roleMapper.mapToDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleDao.findAll();
        return roles.stream()
                .map(roleMapper::mapToDto)
                .toList(); // note: toList() only works from java 16+
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role existingRole = roleUtils.findRoleById(id);
        roleMapper.updateEntityFromDto(roleDto,existingRole);

        Role savedRole = roleDao.save(existingRole);
        return roleMapper.mapToDto(savedRole);
    }

    @Override
    public RoleDto softDeleteRole(Long id) {
        log.info("Soft deleting role with ID: {}", id);
        Role role = roleUtils.findRoleById(id);

        role.setIsActive(false);
        Role softDeleteRole = roleDao.save(role);
        return roleMapper.mapToDto(softDeleteRole);
    }
}
