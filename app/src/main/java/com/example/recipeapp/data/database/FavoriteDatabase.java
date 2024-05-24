package com.example.recipeapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recipeapp.data.dao.FavoriteDAO;
import com.example.recipeapp.data.entities.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {
    public abstract FavoriteDAO favoriteDao();

}
