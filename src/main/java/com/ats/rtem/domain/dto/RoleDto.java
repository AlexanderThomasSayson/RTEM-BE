package com.ats.rtem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
