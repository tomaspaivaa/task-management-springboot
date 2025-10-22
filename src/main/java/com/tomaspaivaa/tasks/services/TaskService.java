package com.tomaspaivaa.tasks.services;

import com.tomaspaivaa.tasks.domain.entities.Task;
import com.tomaspaivaa.tasks.domain.entities.TaskList;
import com.tomaspaivaa.tasks.domain.entities.TaskPriority;
import com.tomaspaivaa.tasks.domain.entities.TaskStatus;
import com.tomaspaivaa.tasks.repositories.ITaskListRepository;
import com.tomaspaivaa.tasks.repositories.ITaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository iTaskRepository;
    private final ITaskListRepository iTaskListRepository;

    public TaskService(ITaskRepository iTaskRepository, ITaskListRepository iTaskListRepository) {
        this.iTaskRepository = iTaskRepository;
        this.iTaskListRepository = iTaskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return iTaskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("This task list already has and ID");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("This task doesn't have a title");
        }

        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskPriority taskPriority = task.getPriority();
        if (task.getPriority() == null) {
            taskPriority = TaskPriority.MEDIUM;
        }

        TaskList taskList = iTaskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("There is no TaskListr with id: " + taskListId));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                now,
                now,
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList
        );

        return iTaskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return iTaskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("This task doesn't have an ID");
        }
        if (!task.getId().equals(taskId)) {
            throw new IllegalArgumentException("The ID of the task is not equal to taskId");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("The task has null priority");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("The task has null status");
        }

        Task existingTask = iTaskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());

        return iTaskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        iTaskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
