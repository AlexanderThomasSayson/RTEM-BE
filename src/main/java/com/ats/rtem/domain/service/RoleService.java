package com.ats.rtem.domain.service;

import com.ats.rtem.domain.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    RoleDto updateRole(Long id, RoleDto roleDto);

    RoleDto softDeleteRole(Long id);
}
