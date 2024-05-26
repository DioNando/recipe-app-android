package com.example.recipeapp.ui.favorite;

import android.content.Intent;
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
import com.example.recipeapp.LoginActivity;
import com.example.recipeapp.R;
import com.example.recipeapp.data.Recipe;
import com.example.recipeapp.data.dao.FavoriteDAO;
import com.example.recipeapp.data.database.FavoriteDatabase;
import com.example.recipeapp.data.entities.Favorite;
import com.example.recipeapp.databinding.FragmentFavoriteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    private FavoriteDatabase favoriteDatabase;
    private FavoriteDAO favoriteDAO;
    private FavoriteAdapter favoriteAdapter;

    private FirebaseAuth mAuth;
    FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish(); // Optionnel : fermer l'activité actuelle
        }else{

        }

        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewFavorite);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        favoriteAdapter = new FavoriteAdapter(new ArrayList<>(), favoriteClickListener);
        recyclerView1.setAdapter(favoriteAdapter);

        favoriteDatabase = FavoriteDatabase.getInstance(getContext());
        favoriteDAO = favoriteDatabase.favoriteDAO();

        loadFavorites();

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

    private void loadFavorites() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            final List<Favorite> favorites = favoriteDAO.getAllFavorites();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> favoriteAdapter.setFavorites(favorites));
            }
        });
    }
}