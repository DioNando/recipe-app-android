package com.example.recipeapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("readyInMinutes")
    private int readyInMinutes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @SerializedName("servings")
    private int servings;

    @SerializedName("sourceUrl")
    private String sourceUrl;

    @SerializedName("summary")
    private String summary;

    @SerializedName("extendedIngredients")
    private List<Ingredient> ingredients;

    // Constructeurs, getters et setters

    // Définition des classes auxiliaires
    public static class Ingredient {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("image")
        private String imageUrl;

        // Constructeurs, getters et setters
    }

    // Constructeurs, getters et setters
}