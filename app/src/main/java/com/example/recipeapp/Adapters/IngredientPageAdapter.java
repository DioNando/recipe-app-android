package com.example.recipeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.Listeners.IngredientClickListener;
import com.example.recipeapp.R;

public class IngredientPageAdapter extends RecyclerView.Adapter<IngredientPageAdapter.CardViewHolder> {

    IngredientClickListener listener;
    public IngredientPageAdapter(IngredientClickListener listener) {
        this.listener=listener;
    }
    @NonNull
    @Override
    public IngredientPageAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_page, parent, false);
        return new IngredientPageAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientPageAdapter.CardViewHolder holder, int position) {
        holder.ingredient_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIngredientClicked(String.valueOf(3432));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView ingredient_list_container;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_list_container=itemView.findViewById(R.id.ingredient_list_container);
        }
    }
}
