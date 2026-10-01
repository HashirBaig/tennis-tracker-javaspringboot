package com.hashir.tennistracker.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ApiResponse<T>(
        String message,
        T data,
        @JsonInclude(JsonInclude.Include.NON_NULL) Pagination pagination
) {
    // keeps existing two-argument usages working
    public ApiResponse(String message, T data) {
        this(message, data, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> paginated(T data, Pagination pagination) {
        return new ApiResponse<>("success", data, pagination);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, null);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(message, data);
    }
}