package com.example.newtaskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.newtaskmanager.database.TaskDBSchema.TaskTable.Cols;

public class TaskDBHelper extends SQLiteOpenHelper {

    public TaskDBHelper(@Nullable Context context) {
        super(context, TaskDBSchema.NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE " + TaskDBSchema.TaskTable.NAME + "(");
        sbQuery.append(Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbQuery.append(Cols.UUId + " TEXT NOT NULL,");
        sbQuery.append(Cols.TITLE + " TEXT,");
        sbQuery.append(Cols.DESCRIPTION + " TEXT");
        sbQuery.append(");");
        sqLiteDatabase.execSQL(sbQuery.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
