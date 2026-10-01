package com.hashir.tennistracker.common;

import org.springframework.data.domain.Page;

public record Pagination(
        int page,
        int limit,
        long totalCount,
        int totalPages,
        boolean hasNextPage,
        boolean hasPrevPage
) {
    public static Pagination fromPage(Page<?> result) {
        return new Pagination(
                result.getNumber() + 1,   // Spring pages are 0-based, your API is 1-based
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.hasNext(),
                result.hasPrevious()
        );
    }
}