package com.ats.rtem.domain.service.Impl;

import com.ats.rtem.common.mapper.RoleMapper;
import com.ats.rtem.common.utils.RoleUtils;
import com.ats.rtem.domain.dao.RoleDao;
import com.ats.rtem.domain.dto.RoleDto;
import com.ats.rtem.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private RoleUtils roleUtils;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole_Success() {
        RoleDto dto = new RoleDto();
        dto.setRoleName("Admin");

        Role role = new Role();
        Role savedRole = new Role();
        savedRole.setId(1L);

        RoleDto savedDto = new RoleDto();
        savedDto.setId(1L);

        when(roleMapper.mapToEntity(dto)).thenReturn(role);
        when(roleDao.save(role)).thenReturn(savedRole);
        when(roleMapper.mapToDto(savedRole)).thenReturn(savedDto);

        RoleDto result = roleService.createRole(dto);

        assertEquals(1L, result.getId());
        verify(roleDao).save(role);
    }

    @Test
    void testCreateRole_NullInput() {
        assertThrows(NullPointerException.class, () -> roleService.createRole(null));
    }

    @Test
    void testGetRoleById_Found() {
        Role role = new Role();
        role.setId(1L);

        RoleDto dto = new RoleDto();
        dto.setId(1L);

        when(roleUtils.findRoleById(1L)).thenReturn(role);
        when(roleMapper.mapToDto(role)).thenReturn(dto);

        RoleDto result = roleService.getRoleById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testGetRoleById_NotFound() {
        when(roleUtils.findRoleById(999L)).thenThrow(new RuntimeException("Role not found"));

        Exception ex = assertThrows(RuntimeException.class, () -> roleService.getRoleById(999L));
        assertEquals("Role not found", ex.getMessage());
    }

    @Test
    void testGetAllRoles_EmptyList() {
        when(roleDao.findAll()).thenReturn(Collections.emptyList());

        List<RoleDto> result = roleService.getAllRoles();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllRoles_MultipleRoles() {
        Role role1 = new Role();
        Role role2 = new Role();
        List<Role> roles = Arrays.asList(role1, role2);

        RoleDto dto1 = new RoleDto();
        RoleDto dto2 = new RoleDto();

        when(roleDao.findAll()).thenReturn(roles);
        when(roleMapper.mapToDto(role1)).thenReturn(dto1);
        when(roleMapper.mapToDto(role2)).thenReturn(dto2);

        List<RoleDto> result = roleService.getAllRoles();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateRole_Success() {
        RoleDto dto = new RoleDto();
        dto.setRoleName("Updated");

        Role existingRole = new Role();
        Role savedRole = new Role();

        RoleDto savedDto = new RoleDto();
        savedDto.setRoleName("Updated");

        when(roleUtils.findRoleById(1L)).thenReturn(existingRole);
        doNothing().when(roleMapper).updateEntityFromDto(dto, existingRole);
        when(roleDao.save(existingRole)).thenReturn(savedRole);
        when(roleMapper.mapToDto(savedRole)).thenReturn(savedDto);

        RoleDto result = roleService.updateRole(1L, dto);

        assertEquals("Updated", result.getRoleName());
    }

    @Test
    void testUpdateRole_NonexistentId() {
        RoleDto dto = new RoleDto();
        when(roleUtils.findRoleById(999L)).thenThrow(new RuntimeException("Role not found"));

        Exception ex = assertThrows(RuntimeException.class, () -> roleService.updateRole(999L, dto));
        assertEquals("Role not found", ex.getMessage());
    }

    @Test
    void testSoftDeleteRole_AlreadyInactive() {
        Role role = new Role();
        role.setIsActive(false); // Already inactive

        RoleDto dto = new RoleDto();
        dto.setIsActive(false);

        when(roleUtils.findRoleById(1L)).thenReturn(role);
        when(roleDao.save(role)).thenReturn(role);
        when(roleMapper.mapToDto(role)).thenReturn(dto);

        RoleDto result = roleService.softDeleteRole(1L);
        assertFalse(result.getIsActive());
        verify(roleDao).save(role);
    }

    @Test
    void testSoftDeleteRole_ActiveToInactive() {
        Role role = new Role();
        role.setIsActive(true); // Active before deletion

        RoleDto dto = new RoleDto();
        dto.setIsActive(false);

        when(roleUtils.findRoleById(1L)).thenReturn(role);
        when(roleDao.save(role)).thenReturn(role);
        when(roleMapper.mapToDto(role)).thenReturn(dto);

        RoleDto result = roleService.softDeleteRole(1L);
        assertFalse(result.getIsActive());
        verify(roleDao).save(role);
    }
}
