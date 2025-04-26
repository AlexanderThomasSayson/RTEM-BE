package com.ats.rtem.common.utils;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class DefaultResponse {

    /**
     * Creates an API response with the specified parameters.
     *
     * @param status    the HTTP status to be returned in the response.
     * @param success   indicates whether the operation was successful.
     * @param message   a message providing additional details about the response.
     * @param data      the data object to include in the response body.
     * @param errorCode the error code associated with the response, if applicable.
     * @param <T>       the type of the data object.
     * @return an {@link ApiResponse} object with the specified parameters.
     */
    private static <T> ApiResponse<T> createResponse(
            HttpStatus status,
            boolean success,
            String message,
            T data,
            int errorCode) {
        return new ApiResponse<>(
                status,
                success,
                message,
                data,
                null,
                errorCode,
                LocalDateTime.now(),
                null // path
        );
    }

    /**
     * Creates a response for a successfully created object.
     *
     * @param object the created object to include in the response body.
     * @param <T>    the type of the object.
     * @return an {@link ApiResponse} with HTTP status 201 (CREATED) and the object.
     */
    public static <T> ApiResponse<T> displayCreatedObject(T object) {
        return createResponse(HttpStatus.CREATED, true, "Object(s) created", object, 0);
    }

    /**
     * Creates a response for a successfully found object.
     *
     * @param object the found object to include in the response body.
     * @param <T>    the type of the object.
     * @return an {@link ApiResponse} with HTTP status 200 (OK) and the object.
     */
    public static <T> ApiResponse<T> displayFoundObject(T object) {
        return createResponse(HttpStatus.OK, true, "Object(s) found", object, 0);
    }

    /**
     * Creates a response for a successfully updated object.
     *
     * @param object the updated object to include in the response body.
     * @param <T>    the type of the object.
     * @return an {@link ApiResponse} with HTTP status 200 (OK) and the object.
     */
    public static <T> ApiResponse<T> displayUpdatedObject(T object) {
        return createResponse(HttpStatus.OK, true, "Object updated", object, 0);
    }

    /**
     * Creates a response for a successful login.
     *
     * @param object the authenticated object (e.g., AuthResponseDto) to include in the response body.
     * @param <T>    the type of the object.
     * @return an {@link ApiResponse} with HTTP status 200 (OK) and the object.
     */
    public static <T> ApiResponse<T> displayLoginSuccess(T object) {
        return createResponse(HttpStatus.OK, true, "User logged in successfully", object, 0);
    }


    /**
     * Returns a response indicating no content was found.
     *
     * @return ApiResponse<Void> with HTTP status 204 (NO_CONTENT) and a message "No
     * object(s) found".
     */
    public static ApiResponse<Void> displayNoContent() {
        return createResponse(HttpStatus.NO_CONTENT, false, "No object(s) found", null, 0);
    }

    /**
     * Returns a response indicating a bad request (400).
     */
    public static ApiResponse<Void> displayBadRequest(String message) {
        return createResponse(HttpStatus.BAD_REQUEST, false, message, null, 400);
    }

    /**
     * Returns a response indicating unauthorized access (401).
     */
    public static ApiResponse<Void> displayUnauthorized(String message) {
        return createResponse(HttpStatus.UNAUTHORIZED, false, message, null, 401);
    }

    /**
     * Returns a response indicating forbidden access (403).
     */
    public static ApiResponse<Void> displayForbidden(String message) {
        return createResponse(HttpStatus.FORBIDDEN, false, message, null, 403);
    }

    /**
     * Returns a response indicating resource not found (404).
     */
    public static ApiResponse<Void> displayNotFound(String message) {
        return createResponse(HttpStatus.NOT_FOUND, false, message, null, 404);
    }

    /**
     * Returns a response indicating an internal server error (500).
     */
    public static ApiResponse<Void> displayServerError(String message) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, message, null, 500);
    }
}