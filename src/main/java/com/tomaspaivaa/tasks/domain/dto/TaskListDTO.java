package com.tomaspaivaa.tasks.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDTO(
        UUID id,
        String title,
        String description,
        int tasksCounter,
        Double progress,
        List<TaskDTO> tasks
) {
}
