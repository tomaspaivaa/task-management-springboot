package com.tomaspaivaa.tasks.controllers;

import com.tomaspaivaa.tasks.domain.dto.TaskListDTO;
import com.tomaspaivaa.tasks.domain.entities.TaskList;
import com.tomaspaivaa.tasks.mappers.ITaskListMapper;
import com.tomaspaivaa.tasks.services.ITaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final ITaskListService iTaskListService;
    private final ITaskListMapper iTaskListMapper;

    public TaskListController(ITaskListService iTaskListService, ITaskListMapper iTaskListMapper) {
        this.iTaskListService = iTaskListService;
        this.iTaskListMapper = iTaskListMapper;
    }

    @GetMapping
    public List<TaskListDTO> listTaskLists() {
        return iTaskListService.listTaskLists()
                .stream()
                .map(iTaskListMapper::toDTO)
                .toList();
    }

    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTaskList = iTaskListService.createTaskList(
                iTaskListMapper.fromDTO(taskListDTO)
        );

        return iTaskListMapper.toDTO(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDTO> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return iTaskListService.getTaskList(taskListId).map(iTaskListMapper::toDTO);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID taskListId,
                                      @RequestBody TaskListDTO taskListDTO) {
        TaskList updatedTaskList = iTaskListService.updateTaskList(taskListId, iTaskListMapper.fromDTO(taskListDTO));

        return iTaskListMapper.toDTO(updatedTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        iTaskListService.deleteTaskList(taskListId);
    }
}
