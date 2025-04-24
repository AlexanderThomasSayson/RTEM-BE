package com.ats.rtem.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {

    private HttpStatus httpStatus;
    private boolean success;
    private String message;
    private T data;
    private List<T> errors;
    private int errorCode;
    private LocalDateTime timestamp;
    private String path;

}
