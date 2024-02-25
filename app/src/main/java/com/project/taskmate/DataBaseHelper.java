package com.project.taskmate;

import static com.project.taskmate.TaskContract.TaskEntry.CATEGORY;
import static com.project.taskmate.TaskContract.TaskEntry.DESCRIPTION;
import static com.project.taskmate.TaskContract.TaskEntry.ID;
import static com.project.taskmate.TaskContract.TaskEntry.NAME;
import static com.project.taskmate.TaskContract.TaskEntry.PRIORITY;
import static com.project.taskmate.TaskContract.TaskEntry.TABLE_NAME;
import static com.project.taskmate.TaskContract.TaskEntry.TIME;
import static com.project.taskmate.TaskContract.TaskEntry.VERSION;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            DESCRIPTION + " TEXT, " +
            TIME + " DATETIME, " +
            PRIORITY + " INTEGER, " +
            CATEGORY + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public boolean insertUser(String name, String desc, Time time, int pri, String cat){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", name);
        cv.put("DESCRIPTION", desc);
        cv.put("TIME", timeToString(time));
        cv.put("PRIORITY", pri);
        cv.put("CATEGORY", cat);


        Long result = db.insert(TABLE_NAME, null, cv);


        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public ArrayList<TaskModel> getAllTasks() {
        ArrayList<TaskModel> taskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
                @SuppressLint("Range") Time time = Time.valueOf(cursor.getString(cursor.getColumnIndex(TIME)));
                @SuppressLint("Range") int priority = cursor.getInt(cursor.getColumnIndex(PRIORITY));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(CATEGORY));

                // Create TaskModel object and add it to the list
                TaskModel taskModel = new TaskModel(id, name, description, time, category, priority);
                taskList.add(taskModel);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return taskList;
    }



    private String timeToString(Time time) {
        // Convert Time object to string in suitable format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // Define your preferred time format
        return sdf.format(time);
    }
}



