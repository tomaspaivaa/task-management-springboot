package com.tomaspaivaa.tasks.mappers;

import com.tomaspaivaa.tasks.domain.dto.TaskDTO;
import com.tomaspaivaa.tasks.domain.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements ITaskMapper {
    @Override
    public Task fromDTO(TaskDTO taskDTO) {
        return new Task(taskDTO.id(),
                taskDTO.title(),
                taskDTO.description(),
                null,
                null,
                taskDTO.dueDate(),
                taskDTO.status(),
                taskDTO.priority(),
                null);
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority());
    }
}
