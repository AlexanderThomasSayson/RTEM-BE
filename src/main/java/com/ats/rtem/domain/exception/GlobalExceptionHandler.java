package com.ats.rtem.domain.exception;

import com.ats.rtem.common.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles MethodArgumentNotValidException for validation errors and returns a
     * 400 Bad Request status.
     *
     * @param ex the MethodArgumentNotValidException thrown when method arguments
     *           fail validation
     * @return ResponseEntity containing an ApiResponse with error details and an
     *         HTTP status code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleArgumentMethod(MethodArgumentNotValidException ex) {
        // Create the error details
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Create the ApiResponse object
        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setMessage("Validation failed for one or more arguments.");
        response.setData(errors);
        response.setErrors(List.of(errors));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setMessage(ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(BadRequestException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Bad Request: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException ex) {
        ApiResponse<String> response = new  ApiResponse<>();
        response.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setMessage("Invalid action: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<String>> handleForbiddenException(ForbiddenException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.FORBIDDEN);
        response.setMessage("Access Denied: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<String>> handleConflictException(ConflictException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.CONFLICT);
        response.setMessage("Conflict: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidUserRoleException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidUserAction(InvalidUserRoleException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setMessage("Invalid action: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentialsException(BadCredentialsException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Invalid username or password");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("User not found");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setMessage(ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
