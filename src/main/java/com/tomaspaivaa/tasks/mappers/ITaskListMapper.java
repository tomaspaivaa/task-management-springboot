package com.tomaspaivaa.tasks.mappers;

import com.tomaspaivaa.tasks.domain.dto.TaskListDTO;
import com.tomaspaivaa.tasks.domain.entities.TaskList;

public interface ITaskListMapper {
    TaskList fromDTO(TaskListDTO taskListDTO);

    TaskListDTO toDTO(TaskList taskList);
}
