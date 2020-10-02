package com.example.newtaskmanager.database;

public class TaskDBSchema {
    public static final String NAME = "task.db";
    public static final int VERSION = 1;

    public static final class TaskTable {
        public static final String NAME = "task_table";

        public static final class Cols {
            public static final String ID = "id";
            public static final String UUId = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
        }
    }
}
