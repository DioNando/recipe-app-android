package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CardViewHolder> {

    @NonNull
    @Override
    public FavoriteAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
