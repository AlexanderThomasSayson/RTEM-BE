package com.ats.rtem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String name;
    private String userName;
    private String email;
    private List<String> roles;
    private String bearerToken;
}
