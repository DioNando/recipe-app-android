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

    public Favorite(int recipe_id, String title) {
        this.recipe_id = recipe_id;
        this.title = title;
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
}
