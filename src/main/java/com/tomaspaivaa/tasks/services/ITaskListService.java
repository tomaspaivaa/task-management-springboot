package com.tomaspaivaa.tasks.services;

import com.tomaspaivaa.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskListService {
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList taskList);

    Optional<TaskList> getTaskList(UUID id);

    TaskList updateTaskList(UUID taskListId, TaskList taskList);

    void deleteTaskList(UUID taskListId);
}
