package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

public class IngredientHomeAdapter extends RecyclerView.Adapter<IngredientHomeAdapter.CardViewHolder> {

    @NonNull
    @Override
    public IngredientHomeAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientHomeAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHomeAdapter.CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
