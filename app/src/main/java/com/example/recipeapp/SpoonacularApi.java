package com.example.recipeapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoonacularApi {
    @GET("recipes/search")
    Call<RecipeSearchResponse> searchRecipes(
            @Query("apiKey") String apiKey,
            @Query("query") String query
    );
}
