package com.project.taskmate;

import android.provider.BaseColumns;

public class TaskContract {

    private TaskContract(){}

    public static class TaskEntry implements BaseColumns{
        public static final String TABLE_NAME = "tasks";
        public static final int VERSION = 1;
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String TIME = "time";
        public static final String PRIORITY = "priority";
        public static final String CATEGORY = "category";

    }
}
