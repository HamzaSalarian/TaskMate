package com.project.taskmate;

import androidx.annotation.NonNull;

import java.sql.Time;

public class TaskModel {

    private int id;
    private String task;
    private String description;

    private Time time;

    private String category;

    private int priority;

//    private boolean isCompleted;


//constructor

    public TaskModel(int id, String task, String description, Time time, String category, int priority) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.time = time;
        this.category = category;
        this.priority = priority;
//        this.isCompleted = isCompleted;
    }
    public TaskModel() {
    }

    // Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

//    public boolean isCompleted() {
//        return isCompleted;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

//    public void setCompleted(boolean completed) {
//        isCompleted = completed;
//    }
}



