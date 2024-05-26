package com.example.recipeapp.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
import com.example.recipeapp.LoginActivity;
import com.example.recipeapp.Models.RandomRecipeApiResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.data.dao.FavoriteDAO;
import com.example.recipeapp.data.database.FavoriteDatabase;
import com.example.recipeapp.data.entities.Favorite;
import com.example.recipeapp.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProgressDialog dialog;
    private RequestManager manager;

    private RecyclerView recyclerView1;
    private FavoriteHomeAdapter favoriteHomeAdapter;

    private FavoriteDatabase favoriteDatabase;
    private FavoriteDAO favoriteDAO;

    private FirebaseAuth mAuth;
    FirebaseUser user;

    CardView cardViewFavorite;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dialog = new ProgressDialog(requireContext());
        dialog.setTitle("Loading...");
        manager = new RequestManager(requireContext());
        manager.getRandomRecipes(randomRecipeResponseListener, new ArrayList<>());
        dialog.show();

        recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (user != null) {
            RecyclerView recyclerView3 = root.findViewById(R.id.recyclerViewFavorite);
            LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView3.setLayoutManager(layoutManager3);
            favoriteHomeAdapter = new FavoriteHomeAdapter(new ArrayList<>(), favoriteClickListener);
            recyclerView3.setAdapter(favoriteHomeAdapter);

            favoriteDatabase = FavoriteDatabase.getInstance(getContext());
            favoriteDAO = favoriteDatabase.favoriteDAO();

            loadFavorites();
        }else{


        }



        return root;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            RecipeHomeAdapter adapterHomeRecipe = new RecipeHomeAdapter(requireContext(), response.recipes, recipeClickListener);
            if (user != null) {
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView1.setLayoutManager(layoutManager1);
            } else {
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView1.setLayoutManager(layoutManager1);
            }
            // LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            // recyclerView1.setLayoutManager(layoutManager1);
            recyclerView1.setHasFixedSize(true);
            recyclerView1.setAdapter(adapterHomeRecipe);
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = id -> {
        Bundle bundle = new Bundle();
        bundle.putString("recipe_id", id);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_nav_home_to_nav_detail_recipe, bundle);
    };

    private final FavoriteClickListener favoriteClickListener = id -> {
        Bundle bundle = new Bundle();
        bundle.putString("recipe_id", id);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_nav_home_to_nav_detail_recipe, bundle);
    };

    private void loadFavorites() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            final List<Favorite> favorites = favoriteDAO.getLasted5Favorites();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> favoriteHomeAdapter.setFavorites(favorites));
            }
        });
    }
}
