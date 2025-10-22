package com.tomaspaivaa.tasks.mappers;

import com.tomaspaivaa.tasks.domain.dto.TaskDTO;
import com.tomaspaivaa.tasks.domain.entities.Task;

public interface ITaskMapper {
    Task fromDTO(TaskDTO taskDTO);

    TaskDTO toDTO(Task task);
}
