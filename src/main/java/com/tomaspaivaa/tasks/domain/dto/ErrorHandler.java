package com.tomaspaivaa.tasks.domain.dto;

public record ErrorHandler(
        int status,
        String message,
        String details
) {
}
