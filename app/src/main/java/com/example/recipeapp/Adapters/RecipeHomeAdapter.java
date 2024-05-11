package com.example.recipeapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Recipe;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.CardViewHolder> {

    Context context;
    List<Recipe> recipes;

    public RecipeHomeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.recipes = list;
    }

    @NonNull
    @Override
    public RecipeHomeAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_home, parent, false);
        return new RecipeHomeAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHomeAdapter.CardViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.textView_title.setText(recipe.title);
        holder.textView_title.setSelected(true);
        Log.d("RandomRecipeAdapter", "Chargement de l'image : " + recipes.get(position).image);
        Picasso.get().load(recipe.image).into(holder.imageView_food);
    }

    @Override
    public int getItemCount() {
        //return 8;
        Log.d("RandomRecipeAdapter", "Taille : " + recipes.size());
        return recipes.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView random_list_container;
        TextView textView_title;
        ImageView imageView_food;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container=itemView.findViewById(R.id.random_list_container);
            textView_title=itemView.findViewById(R.id.titleRecipeHome);
            imageView_food=itemView.findViewById(R.id.imageRecipeHome);
        }
    }
}
