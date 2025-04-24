package com.ats.rtem.domain.service.Impl;

import com.ats.rtem.domain.dao.RoleDao;
import com.ats.rtem.domain.dao.UserDao;
import com.ats.rtem.domain.dto.AuthResponseDto;
import com.ats.rtem.domain.dto.LoginDto;
import com.ats.rtem.domain.dto.UserRegistrationDto;
import com.ats.rtem.domain.entity.Role;
import com.ats.rtem.domain.entity.User;
import com.ats.rtem.domain.exception.ResourceNotFoundException;
import com.ats.rtem.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserRegistration_success() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUserName("testuser");
        dto.setFirstName("Test");
        dto.setMiddleName("T");
        dto.setLastName("User");
        dto.setEmail("test@example.com");
        dto.setPhoneNumber("123456789");
        dto.setPassword("password");

        when(userDao.existsByUserName(dto.getUserName())).thenReturn(false);
        when(userDao.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");

        Role userRole = new Role();
        userRole.setRoleName("USER");

        when(roleDao.findByRoleName("USER")).thenReturn(userRole);

        String result = authService.userRegistration(dto);
        assertEquals("User registered successfully!", result);

        verify(userDao).save(any(User.class));
    }

    @Test
    void testUserRegistration_usernameExists() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUserName("existinguser");

        when(userDao.existsByUserName(dto.getUserName())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                authService.userRegistration(dto));
        assertEquals("Username already exists!", exception.getMessage());
    }

    @Test
    void testUserRegistration_emailExists() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUserName("testuser");
        dto.setEmail("test@example.com");

        when(userDao.existsByUserName(dto.getUserName())).thenReturn(false);
        when(userDao.existsByEmail(dto.getEmail())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                authService.userRegistration(dto));
        assertEquals("Email already exists!", exception.getMessage());
    }

    @Test
    void testUserRegistration_roleNotFound() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUserName("testuser");
        dto.setEmail("test@example.com");

        when(userDao.existsByUserName(dto.getUserName())).thenReturn(false);
        when(userDao.existsByEmail(dto.getEmail())).thenReturn(false);
        when(roleDao.findByRoleName("USER")).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () ->
                authService.userRegistration(dto));
    }

    @Test
    void testLogin_success() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserNameOrEmail("testuser");
        loginDto.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn("mocked-jwt-token");

        AuthResponseDto response = authService.login(loginDto);

        assertNotNull(response);
        assertEquals("User logged-in successfully!", response.getMessage());
        assertEquals("mocked-jwt-token", response.getBearerToken());
    }
}
