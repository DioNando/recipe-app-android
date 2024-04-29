package com.example.recipeapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.FavoriteAdapter;
import com.example.recipeapp.Adapters.IngredientAdapter;
import com.example.recipeapp.Adapters.RandomRecipeAdapter;
import com.example.recipeapp.Adapters.RecipeAdapter;
import com.example.recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.recipeapp.Models.RandomRecipeApiResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ProgressDialog dialog;
    private RequestManager manager;

    private RandomRecipeAdapter randomRecipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         dialog = new ProgressDialog(requireContext());
         dialog.setTitle("Loading...");
         manager = new RequestManager(requireContext());
         manager.getRandomRecipies(randomRecipeResponseListener);
         dialog.show();
         recyclerView = binding.recyclerView;
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        RecipeAdapter adapterRecipe = new RecipeAdapter();
        recyclerView1.setAdapter(adapterRecipe);

        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerViewIngredient);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        IngredientAdapter adapterIngredient = new IngredientAdapter();
        recyclerView2.setAdapter(adapterIngredient);

        RecyclerView recyclerView3 = root.findViewById(R.id.recyclerViewFavorite);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        FavoriteAdapter adapterFavorite = new FavoriteAdapter();
        recyclerView3.setAdapter(adapterFavorite);



        return root;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            randomRecipeAdapter = new RandomRecipeAdapter(requireContext(), response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    };
}
