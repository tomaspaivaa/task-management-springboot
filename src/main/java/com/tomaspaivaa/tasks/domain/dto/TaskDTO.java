package com.tomaspaivaa.tasks.domain.dto;

import com.tomaspaivaa.tasks.domain.entities.TaskPriority;
import com.tomaspaivaa.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
