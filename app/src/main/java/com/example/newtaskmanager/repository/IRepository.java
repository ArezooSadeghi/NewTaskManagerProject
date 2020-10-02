package com.example.newtaskmanager.repository;

import com.example.newtaskmanager.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Task> getTasks();

    Task getTask(UUID taskId);

    void insert(Task task);

    void delete(UUID taskId);

    void update(Task task);
}
