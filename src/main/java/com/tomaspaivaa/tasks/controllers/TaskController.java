package com.tomaspaivaa.tasks.controllers;

import com.tomaspaivaa.tasks.domain.dto.TaskDTO;
import com.tomaspaivaa.tasks.domain.entities.Task;
import com.tomaspaivaa.tasks.mappers.ITaskMapper;
import com.tomaspaivaa.tasks.services.ITaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final ITaskService iTaskService;
    private final ITaskMapper iTaskMapper;

    public TaskController(ITaskService iTaskService, ITaskMapper iTaskMapper) {
        this.iTaskService = iTaskService;
        this.iTaskMapper = iTaskMapper;
    }

    @GetMapping
    public List<TaskDTO> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return iTaskService.listTasks(taskListId)
                .stream()
                .map(iTaskMapper::toDTO)
                .toList();
    }

    @PostMapping
    public TaskDTO createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDTO taskDTO) {
        Task createdTask = iTaskService.createTask(
                taskListId,
                iTaskMapper.fromDTO(taskDTO)
        );

        return iTaskMapper.toDTO(createdTask);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDTO> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
        return iTaskService.getTask(taskListId, taskId).map(iTaskMapper::toDTO);
    }

    @PutMapping(path = "/{task_id}")
    public TaskDTO updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = iTaskService.updateTask(taskListId, taskId, iTaskMapper.fromDTO(taskDTO));
        return iTaskMapper.toDTO(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void removeTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
        iTaskService.deleteTask(taskListId, taskId);
    }
}
