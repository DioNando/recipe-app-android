package com.example.recipeapp.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.FavoriteAdapter;
import com.example.recipeapp.Adapters.FavoriteHomeAdapter;
import com.example.recipeapp.Adapters.IngredientAdapter;
import com.example.recipeapp.Adapters.IngredientHomeAdapter;
import com.example.recipeapp.Adapters.RecipeAdapter;
import com.example.recipeapp.Adapters.RecipeHomeAdapter;
import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.R;
import com.example.recipeapp.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewFavorite);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        FavoriteAdapter adapterFavorite = new FavoriteAdapter(favoriteClickListener);
        recyclerView1.setAdapter(adapterFavorite);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private final FavoriteClickListener favoriteClickListener= new FavoriteClickListener() {
        @Override
        public void onFavoriteClicked(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("recipe_id", id);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_nav_favorite_to_nav_detail_recipe, bundle);

        }
    };
}