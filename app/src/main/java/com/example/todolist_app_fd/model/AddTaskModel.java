package com.example.todolist_app_fd.model;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 05/09/2022
 */
public class AddTaskModel {
    private String task;
    private String key;

    public AddTaskModel(String task, String key, String categoriesName) {
        this.task = task;
        this.key = key;
        this.categoriesName = categoriesName;
    }

    public AddTaskModel() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    private String categoriesName;
}
