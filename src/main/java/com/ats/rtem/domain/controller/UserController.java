package com.ats.rtem.domain.controller;

import com.ats.rtem.common.utils.ApiResponse;
import com.ats.rtem.common.utils.DefaultResponse;
import com.ats.rtem.domain.dto.UserDto;
import com.ats.rtem.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "Operations for managing RTEM Users.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create new user", description = "This endpoint allows creation of new user.")
    @PostMapping("/create")
    public ApiResponse<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.createNewUser(userDto);
        return DefaultResponse.displayCreatedObject(savedUser);
    }

}
