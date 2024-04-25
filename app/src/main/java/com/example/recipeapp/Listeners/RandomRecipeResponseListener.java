package com.example.recipeapp.Listeners;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener{

    void didFetch(RandomRecipeApiResponse response,String message);
    void didError(String message);
}
