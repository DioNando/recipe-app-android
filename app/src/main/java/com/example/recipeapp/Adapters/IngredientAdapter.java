package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.ExtendedIngredient;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.CardViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.CardViewHolder holder, int position) {
        holder.textView_ingredient_name.setText(list.get(position).name);
        holder.textView_ingredient_name.setSelected(true);
        holder.textView_ingredient_quantity.setText(list.get(position).original);
        holder.textView_ingredient_quantity.setSelected(true);
        Picasso.get().load("https://img.spoonacular.com/ingredients_100x100/"+list.get(position).image).into(holder.imageView_ingredients);
    }

    @Override
    public int getItemCount() {
        // return 8;
        return list.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView_ingredient_quantity,textView_ingredient_name;
        ImageView imageView_ingredients;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_ingredient_quantity=itemView.findViewById(R.id.textView_ingredient_quantity);
            textView_ingredient_name=itemView.findViewById(R.id.textView_ingredient_name);
            imageView_ingredients=itemView.findViewById(R.id.imageView_ingredient);
        }
    }
}
