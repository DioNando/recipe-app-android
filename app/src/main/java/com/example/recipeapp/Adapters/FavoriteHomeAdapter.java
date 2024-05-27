package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.R;
import com.example.recipeapp.data.entities.Favorite;

import java.util.List;

public class FavoriteHomeAdapter extends RecyclerView.Adapter<FavoriteHomeAdapter.CardViewHolder> {

    FavoriteClickListener listener;
    private List<Favorite> favorites;

    public FavoriteHomeAdapter(List<Favorite> favorites, FavoriteClickListener listener) {
        this.favorites = favorites;
        this.listener=listener;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteHomeAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_home, parent, false);
        return new FavoriteHomeAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHomeAdapter.CardViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);
        holder.textView_favorite_id.setText(String.valueOf(favorite.getRecipe_id()));
        holder.textView_favorite_name.setText(favorite.getTitle());
        holder.favorite_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavoriteClicked(String.valueOf(favorite.getRecipe_id()));
            }
        });
    }

    @Override
    public int getItemCount() {
        // return 3;
        return favorites.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView favorite_list_container;
        TextView textView_favorite_id,textView_favorite_name, textView_favorite_date;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite_list_container=itemView.findViewById(R.id.favorite_list_container);
            textView_favorite_id=itemView.findViewById(R.id.textView_favorite_id);
            textView_favorite_name=itemView.findViewById(R.id.textView_favorite_name);
            textView_favorite_date=itemView.findViewById(R.id.textView_favorite_date);
        }
    }
}
