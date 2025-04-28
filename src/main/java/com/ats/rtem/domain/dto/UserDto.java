package com.ats.rtem.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{7,15}", message = "Phone number must be between 7 to 15 digits")
    private String phoneNumber;

    @Size(max = 10, message = "Country code must not exceed 10 characters")
    private String countryCode;

    @Size(max = 50, message = "Region must not exceed 50 characters")
    private String region;

    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Size(max = 50, message = "District must not exceed 50 characters")
    private String district;

    @Size(max = 50, message = "Barangay must not exceed 50 characters")
    private String barangay;

    @Size(max = 100, message = "Street address must not exceed 100 characters")
    private String streetAddress;

    @Size(max = 10, message = "Postal code must not exceed 10 characters")
    private String postalCode;

    @Size(max = 20, message = "SSS number must not exceed 20 characters")
    private String sssNumber;

    @Size(max = 20, message = "TIN number must not exceed 20 characters")
    private String tinNumber;

    @Size(max = 20, message = "Pagibig number must not exceed 20 characters")
    private String pagibigNumber;

    @Size(max = 20, message = "Philhealth number must not exceed 20 characters")
    private String philhealthNumber;

    @NotNull(message = "Status ID is required")
    private Long statusId;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private Set<RoleDto> roles;
}
