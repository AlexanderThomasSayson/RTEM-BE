package com.ats.rtem.domain.controller;

import com.ats.rtem.common.utils.ApiResponse;
import com.ats.rtem.common.utils.DateUtils;
import com.ats.rtem.common.utils.DefaultResponse;
import com.ats.rtem.domain.dto.UserDto;
import com.ats.rtem.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "Operations for managing RTEM users.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Search users", description = "This endpoint allows searching of users by keyword or date range.")
    @GetMapping("/all")
    public ApiResponse<Page<UserDto>> searchUser(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "date_created") String sortBy,
        @RequestParam(defaultValue = "desc") String sortDirection){

        // Use the utility class to parse dates
        LocalDateTime startDateTime = DateUtils.parseStartDateToLocalDateTime(startDate);
        LocalDateTime endDateTime = DateUtils.parseEndDateToLocalDateTime(endDate);

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserDto> searchUser = userService.searchUsers(keyword,startDateTime,endDateTime,pageable);
        return DefaultResponse.displayFoundObject(searchUser);
    }
}
