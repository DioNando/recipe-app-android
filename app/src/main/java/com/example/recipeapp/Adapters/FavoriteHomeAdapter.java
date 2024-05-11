package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

public class FavoriteHomeAdapter extends RecyclerView.Adapter<FavoriteHomeAdapter.CardViewHolder> {

    @NonNull
    @Override
    public FavoriteHomeAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteHomeAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHomeAdapter.CardViewHolder holder, int position) {

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
