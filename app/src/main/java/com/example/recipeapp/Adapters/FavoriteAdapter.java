package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.R;
import com.example.recipeapp.data.entities.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CardViewHolder> {

    private List<Favorite> favorites;
    FavoriteClickListener listener;

    public FavoriteAdapter(List<Favorite> favorites, FavoriteClickListener listener) {
        this.favorites = favorites;
        this.listener = listener;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.CardViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);
        holder.textView_favorite_id.setText(String.valueOf(favorite.getRecipe_id()));
        holder.textView_favorite_name.setText(favorite.getTitle());
        holder.favorite_list_container.setOnClickListener(v ->
                listener.onFavoriteClicked(String.valueOf(favorite.getRecipe_id()))
        );
        holder.button_favorite_delete.setOnClickListener(v ->
                listener.onFavoriteDeleteClicked(String.valueOf(favorite.getId()), favorite.getTitle())
        );
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView favorite_list_container;
        ImageButton button_favorite_delete;
        TextView textView_favorite_id, textView_favorite_name;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite_list_container = itemView.findViewById(R.id.favorite_list_container);
            textView_favorite_id = itemView.findViewById(R.id.textView_favorite_id);
            textView_favorite_name = itemView.findViewById(R.id.textView_favorite_name);
            button_favorite_delete = itemView.findViewById(R.id.button_favorite_delete);
        }
    }
}
