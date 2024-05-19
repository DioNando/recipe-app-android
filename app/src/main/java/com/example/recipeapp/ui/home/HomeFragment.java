package com.example.recipeapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.FavoriteHomeAdapter;
import com.example.recipeapp.Adapters.RecipeHomeAdapter;
import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.Models.RandomRecipeApiResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ProgressDialog dialog;
    private RequestManager manager;

    //private RandomRecipeAdapter randomRecipeAdapter;
    private RecyclerView recyclerView1;
    List<String> tags = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dialog = new ProgressDialog(requireContext());
        dialog.setTitle("Loading...");
        manager = new RequestManager(requireContext());
        manager.getRandomRecipes(randomRecipeResponseListener,tags);
        dialog.show();

        recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);

        /* RecyclerView recyclerView2 = root.findViewById(R.id.recyclerViewIngredient);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        IngredientHomeAdapter adapterHomeIngredient = new IngredientHomeAdapter();
        recyclerView2.setAdapter(adapterHomeIngredient); */

        RecyclerView recyclerView3 = root.findViewById(R.id.recyclerViewFavorite);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        FavoriteHomeAdapter adapterHomeFavorite = new FavoriteHomeAdapter(favoriteClickListener);
        recyclerView3.setAdapter(adapterHomeFavorite);



        return root;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();

            RecipeHomeAdapter adapterHomeRecipe = new RecipeHomeAdapter(requireContext(), response.recipes, recipeClickListener);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView1.setLayoutManager(layoutManager1);
            recyclerView1.setHasFixedSize(true);
            recyclerView1.setAdapter(adapterHomeRecipe);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("recipe_id", id);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_nav_home_to_nav_detail_recipe, bundle);

        }
    };

    private final FavoriteClickListener favoriteClickListener= new FavoriteClickListener() {
        @Override
        public void onFavoriteClicked(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("recipe_id", id);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_nav_home_to_nav_detail_recipe, bundle);

        }
    };
}
