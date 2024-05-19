package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CardViewHolder> {

    FavoriteClickListener listener;
    public FavoriteAdapter(FavoriteClickListener listener) {
    this.listener=listener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.CardViewHolder holder, int position) {
        holder.favorite_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavoriteClicked(String.valueOf(6432));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView favorite_list_container;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite_list_container=itemView.findViewById(R.id.favorite_list_container);
        }
    }
}
