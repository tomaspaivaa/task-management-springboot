package com.tomaspaivaa.tasks.services;

import com.tomaspaivaa.tasks.domain.entities.TaskList;
import com.tomaspaivaa.tasks.repositories.ITaskListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListService implements ITaskListService {
    private final ITaskListRepository iTaskListRepository;

    public TaskListService(ITaskListRepository iTaskListRepository) {
        this.iTaskListRepository = iTaskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return iTaskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("This task list already has and ID");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("This task doesn't have a title");
        }

        LocalDateTime now = LocalDateTime.now();
        return iTaskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                now,
                now,
                null

        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return iTaskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (taskList.getId() == null) {
            throw new IllegalArgumentException("This TaskList doesn't have an ID");
        }
        if (!taskList.getId().equals(taskListId)) {
            throw new IllegalArgumentException("The ID of the taskList is not equal to taskListId");
        }

        TaskList foundedTaskList = iTaskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("There is no taskList with the given taskListId"));

        foundedTaskList.setTitle(taskList.getTitle());
        foundedTaskList.setDescription(taskList.getDescription());
        foundedTaskList.setUpdated(LocalDateTime.now());

        return iTaskListRepository.save(foundedTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        iTaskListRepository.deleteById(taskListId);
    }
}
