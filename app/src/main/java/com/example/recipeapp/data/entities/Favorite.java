package com.example.recipeapp.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class Favorite {

    @ColumnInfo(name = "favorite_id")
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "recipe_id")
    int recipe_id;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "date_added")
    String date_added;

    public Favorite(int recipe_id, String title, String date_added) {
        this.recipe_id = recipe_id;
        this.title = title;
        this.date_added = date_added;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
