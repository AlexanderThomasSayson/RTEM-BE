package com.ats.rtem.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Schema(
            description = "Username or Email",
            example = "john.doe@example.com"
    )
    @NotBlank(message = "Username or Email is required!")
    @Size(min = 4, max = 50, message = "Username or Email must be between 4 and 50 characters.")
    @Pattern(
            regexp = "^(?:[a-zA-Z0-9_]+|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Enter a valid username or email."
    )
    private String userNameOrEmail;

    @Schema(
            description = "User password",
            example = "P@ssw0rd123"
    )
    @NotBlank(message = "Password is required!")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;
}
