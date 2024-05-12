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

import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.Models.Recipe;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CardViewHolder> {
    Context context;
    List<Recipe> recipes;
    RecipeClickListener listener;

    public RecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.recipes = list;
        this.listener=listener;

    }
    @NonNull
    @Override
    public RecipeAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_v2, parent, false);
        return new RecipeAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.CardViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.textView_title.setText(recipe.title);
        holder.textView_title.setSelected(true);
        Log.d("RandomRecipeAdapter", "Chargement de l'image : " + recipes.get(position).image);
        Picasso.get().load(recipe.image).into(holder.imageView_food);
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(recipes.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        // return 8;
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
            textView_title=itemView.findViewById(R.id.titleRecipe);
            imageView_food=itemView.findViewById(R.id.imageRecipe);
        }
    }
}
