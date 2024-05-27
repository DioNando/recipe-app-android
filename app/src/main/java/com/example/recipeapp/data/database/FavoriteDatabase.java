package com.example.recipeapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recipeapp.data.dao.FavoriteDAO;
import com.example.recipeapp.data.entities.Favorite;
import com.example.recipeapp.ui.recipe.DetailRecipeFragment;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase instance;

    public abstract FavoriteDAO favoriteDAO();

    public static synchronized FavoriteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteDatabase.class, "favorite_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
