package com.example.todolist_app_fd.model;

/**
 * Created by Rafaqat Mehmood
 * Whatsapp No:+923101025532
 * 05/09/2022
 */
public class AddCategoriesModel {
    public AddCategoriesModel() {
    }

    public AddCategoriesModel(String categoriesName, String key) {
        this.categoriesName = categoriesName;
        this.key = key;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String categoriesName,key;
}
