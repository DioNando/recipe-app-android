package com.example.recipeapp.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recipeapp.data.entities.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Query("SELECT * FROM Favorite")
    List<Favorite> getAll();

    /* @Query("SELECT * FROM Favorite WHERE id = :id")
    Favorite findById(int id); */

    @Insert
    public void addFavorite(Favorite favorite);

    @Delete
    public void deleteFavorite(Favorite favorite);

}
