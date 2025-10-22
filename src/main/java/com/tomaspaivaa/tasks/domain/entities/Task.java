package com.tomaspaivaa.tasks.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    @Column(name = "description", nullable = true, updatable = true)
    private String description;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "priority", nullable = false)
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    public Task() {
    }

    public Task(UUID id, String title, String description, LocalDateTime created, LocalDateTime updated,
                LocalDateTime dueDate, TaskStatus status, TaskPriority priority, TaskList taskList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getCreated(), task.getCreated()) && Objects.equals(getUpdated(), task.getUpdated()) && Objects.equals(getDueDate(), task.getDueDate()) && getStatus() == task.getStatus() && getPriority() == task.getPriority() && Objects.equals(getTaskList(), task.getTaskList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCreated(), getUpdated(), getDueDate(), getStatus(), getPriority(), getTaskList());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", taskList=" + taskList +
                '}';
    }
}

