package com.example.newtaskmanager.model;

import java.util.UUID;

public class Task {
    private String mTitle;
    private String mDescription;
    private UUID mId;

    public Task() {
        this(UUID.randomUUID());
    }

    public Task(UUID id) {
        mId = id;
    }

    public Task(String title, String description, UUID id) {
        mTitle = title;
        mDescription = description;
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public UUID getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
