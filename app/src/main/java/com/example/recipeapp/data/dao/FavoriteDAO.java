package com.example.recipeapp.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recipeapp.data.entities.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Query("SELECT * FROM Favorite ORDER BY favorite_id DESC")
    List<Favorite> getAllFavorites();

    /* @Query("SELECT * FROM Favorite WHERE id = :id")
    Favorite findById(int id); */

    @Query("SELECT * FROM Favorite ORDER BY favorite_id DESC LIMIT 5")
    List<Favorite> getLasted5Favorites();

    @Insert
    public void addFavorite(Favorite favorite);

    @Query("DELETE FROM Favorite WHERE favorite_id = :id")
    public void deleteFavorite(String id);

}
