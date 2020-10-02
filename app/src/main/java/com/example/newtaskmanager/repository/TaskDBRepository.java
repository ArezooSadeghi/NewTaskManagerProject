package com.example.newtaskmanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newtaskmanager.database.TaskDBHelper;
import com.example.newtaskmanager.database.TaskDBSchema;
import com.example.newtaskmanager.database.TaskDBSchema.TaskTable.Cols;
import com.example.newtaskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDBRepository implements IRepository {
    private static TaskDBRepository sInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private TaskDBRepository(Context context) {
        mContext = context;
        TaskDBHelper taskDBHelper = new TaskDBHelper(mContext);
        mDatabase = taskDBHelper.getWritableDatabase();
    }

    public static TaskDBRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TaskDBRepository(context);
        }
        return sInstance;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Cursor cursor = mDatabase.query(TaskDBSchema.TaskTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor == null || cursor.getCount() == 0) {
            return tasks;
        }
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = extractTaskFromCursor(cursor);
                tasks.add(task);
                cursor.moveToNext();
            }
            return tasks;
        } finally {
            cursor.close();
        }
    }

    @Override
    public Task getTask(UUID taskId) {
        String selection = Cols.UUId + " = ?";
        String[] selectionArgs = new String[]{taskId.toString()};
        Cursor cursor = mDatabase.query(TaskDBSchema.TaskTable.NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        try {
            cursor.moveToFirst();
            Task task = extractTaskFromCursor(cursor);
            return task;
        } finally {
            cursor.close();
        }
    }

    @Override
    public void insert(Task task) {
        ContentValues values = setContentValues(task);
        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);
    }

    @Override
    public void delete(UUID taskId) {
        String whereClause = Cols.UUId + " = ?";
        String[] whereArgs = new String[]{taskId.toString()};
        mDatabase.delete(TaskDBSchema.TaskTable.NAME, whereClause, whereArgs);
    }

    @Override
    public void update(Task task) {
        ContentValues values = setContentValues(task);
        String whereClause = Cols.UUId + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.update(TaskDBSchema.TaskTable.NAME, values, whereClause, whereArgs);
    }

    private ContentValues setContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUId, task.getId().toString());
        values.put(Cols.TITLE, task.getTitle());
        values.put(Cols.DESCRIPTION, task.getDescription());
        return values;
    }

    private Task extractTaskFromCursor(Cursor cursor) {
        UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(Cols.UUId)));
        String title = cursor.getString(cursor.getColumnIndex(Cols.TITLE));
        String description = cursor.getString(cursor.getColumnIndex(Cols.DESCRIPTION));
        return new Task(title, description, uuid);
    }
}
