package com.tomaspaivaa.tasks.mappers;

import com.tomaspaivaa.tasks.domain.dto.TaskListDTO;
import com.tomaspaivaa.tasks.domain.entities.Task;
import com.tomaspaivaa.tasks.domain.entities.TaskList;
import com.tomaspaivaa.tasks.domain.entities.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapper implements ITaskListMapper {
    private final TaskMapper taskMapper;

    public TaskListMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDTO(TaskListDTO taskListDTO) {
        return new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                null,
                null,
                Optional.ofNullable(taskListDTO.tasks())
                        .map(tasks -> tasks.stream().map(taskMapper::fromDTO).toList())
                        .orElse(null)
        );
    }

    @Override
    public TaskListDTO toDTO(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDTO).toList())
                        .orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null)
            return null;

        int closedTasksCounter = 0;
        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.CLOSED) {
                closedTasksCounter++;
            }
        }

        return (double) closedTasksCounter / tasks.size();
    }
}
