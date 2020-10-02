package com.example.newtaskmanager.repository;

import com.example.newtaskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements IRepository {
    private static TaskRepository sInstance;
    private final static int NUMBER_OF_TASK = 25;
    private List<Task> mTasks;

    private TaskRepository() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TASK; i++) {
            Task task = new Task();
            task.setTitle("HomeWork");
            task.setDescription("I Do My HomeWork.");
            mTasks.add(task);
        }
    }

    public static TaskRepository getInstance() {
        if (sInstance == null) {
            sInstance = new TaskRepository();
        }
        return sInstance;
    }

    @Override
    public List<Task> getTasks() {
        return mTasks;
    }

    @Override
    public Task getTask(UUID taskId) {
        for (Task task : mTasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public void setTasks(List<Task> tasks) {
        mTasks = tasks;
    }

    @Override
    public void insert(Task task) {
        mTasks.add(task);
    }

    @Override
    public void delete(UUID taskId) {
        for (Task task : mTasks) {
            if (task.getId().equals(taskId)) {
                mTasks.remove(task);
                return;
            }
        }
    }

    @Override
    public void update(Task task) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId())) {
                Task findTask = mTasks.get(i);
                findTask.setTitle(task.getTitle());
                findTask.setDescription(task.getDescription());
                return;
            }
        }
    }
}
