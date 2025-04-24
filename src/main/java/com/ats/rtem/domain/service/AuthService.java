package com.ats.rtem.domain.service;

import com.ats.rtem.domain.dto.AuthResponseDto;
import com.ats.rtem.domain.dto.LoginDto;
import com.ats.rtem.domain.dto.UserRegistrationDto;

public interface AuthService {

    String userRegistration(UserRegistrationDto userRegistrationDto);

    AuthResponseDto login(LoginDto loginDto);
}
