package com.example.recipeapp.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.IngredientAdapter;
import com.example.recipeapp.Adapters.IngredientHomeAdapter;
import com.example.recipeapp.Adapters.RecipeAdapter;
import com.example.recipeapp.Adapters.RecipeHomeAdapter;
import com.example.recipeapp.R;
import com.example.recipeapp.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        // RecipeAdapter adapterRecipe = new RecipeAdapter();
        // recyclerView1.setAdapter(adapterRecipe);

        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerViewIngredient);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        // IngredientAdapter adapterIngredient = new IngredientAdapter();
        // recyclerView2.setAdapter(adapterIngredient);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}