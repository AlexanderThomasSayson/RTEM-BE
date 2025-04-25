package com.ats.rtem.domain.controller;

import com.ats.rtem.common.utils.ApiResponse;
import com.ats.rtem.common.utils.DefaultResponse;
import com.ats.rtem.domain.dto.RoleDto;
import com.ats.rtem.domain.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Role Controller", description = "Operations for managing RTEM Roles.")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Create a new role", description = "This endpoint creates a new role.")
    @PostMapping("/create")
    public ApiResponse<RoleDto> createNewRole(@Valid @RequestBody RoleDto roleDto){
        RoleDto savedRole = roleService.createRole(roleDto);
        return DefaultResponse.displayCreatedObject(savedRole);
    }

    @Operation(summary = "Get role by ID", description = "This endpoint retrieves a role by it's ID.")
    @GetMapping("/getById/{id}")
    public ApiResponse<RoleDto> getRoleById(@PathVariable("id") Long id){
        RoleDto roleDto = roleService.getRoleById(id);
        return DefaultResponse.displayFoundObject(roleDto);
    }

    @Operation(summary = "Retrieve all the existing roles", description = "This endpoint get all the existing roles.")
    @GetMapping("/all")
    public ApiResponse<List<RoleDto>> getAllRoles(){
        List<RoleDto> roles = roleService.getAllRoles();
        return DefaultResponse.displayFoundObject(roles);
    }

    @Operation(summary = "Update a role", description = "This endpoint allows updating of role.")
    @PutMapping("/update/{id}")
    public ApiResponse<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto, @PathVariable("id") Long id){
        RoleDto updatedRole = roleService.updateRole(id, roleDto);
        return DefaultResponse.displayUpdatedObject(updatedRole);
    }

    @Operation(summary = "Soft delete role", description = "This endpoint soft-delete a role by it's ID.")
    @DeleteMapping("/soft-delete/{id}")
    public ApiResponse<RoleDto>softDeleteRole(@PathVariable("id") Long id){
        RoleDto softDeleteRole = roleService.softDeleteRole(id);
        return DefaultResponse.displayUpdatedObject(softDeleteRole);
    }

}
