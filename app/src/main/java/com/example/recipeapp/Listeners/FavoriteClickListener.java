package com.example.recipeapp.Listeners;

public interface FavoriteClickListener {
    void onFavoriteClicked(String id);

    void onFavoriteDeleteClicked(String id, String title);
}
