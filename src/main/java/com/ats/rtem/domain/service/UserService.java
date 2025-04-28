package com.ats.rtem.domain.service;

import com.ats.rtem.domain.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface UserService {

    Page<UserDto> searchUsers(String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
