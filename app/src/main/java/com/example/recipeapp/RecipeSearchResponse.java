package com.example.recipeapp;
import com.example.recipeapp.data.Recipe;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecipeSearchResponse {
    @SerializedName("results")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
// Constructeurs, getters et setters
}