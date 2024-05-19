package com.example.recipeapp;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        String imageUrl = "https://img.spoonacular.com/recipes/" + recipe.getId() + "-556x370.jpg";
        Log.d("RecipeAdapter", "Loading image from URL: " + imageUrl);
        Picasso.get().load(imageUrl).placeholder(R.drawable.bg1).into(holder.imageView);
        Log.d("RecipeAdapter", "Image loading completed for URL: " + imageUrl);
    }

    @Override
    public int getItemCount() {
        Log.d("RecipeAdapter", "getItemCount: " + recipes.size());
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
        }
    }
}