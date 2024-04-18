package com.example.recipeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.data.Recipe;
import com.example.recipeapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecipeAdapter recipeAdapter;

    private SpoonacularApi spoonacularApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action de votre FloatingActionButton
            }
        });


        // Initialiser le RecyclerView
        RecyclerView recyclerView = binding.recyclerView;


        // Spécifier le nombre de colonnes pour le GridLayoutManager
        int numberOfColumns = 2; // Vous pouvez ajuster ce nombre selon vos préférences

        // Créer un GridLayoutManager avec le nombre de colonnes spécifié
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, numberOfColumns, RecyclerView.VERTICAL, false);

        // Associer le GridLayoutManager à votre RecyclerView
        recyclerView.setLayoutManager(layoutManager);


        recipeAdapter = new RecipeAdapter(new ArrayList<>());
        recyclerView.setAdapter(recipeAdapter);

        spoonacularApi = RetrofitClient.getClient().create(SpoonacularApi.class);

        // Effectuez la recherche de recettes
        searchRecipes("3b603fcae7834594ae55b4052a02428d", "pasta");
    }

    private void searchRecipes(String apiKey, String query) {
        Call<RecipeSearchResponse> call = spoonacularApi.searchRecipes(apiKey, query);
        call.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeSearchResponse> call, @NonNull Response<RecipeSearchResponse> response) {
                if (response.isSuccessful()) {
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if (recipeSearchResponse != null && recipeSearchResponse.getRecipes() != null) {
                        List<Recipe> recipes = recipeSearchResponse.getRecipes();
                        Log.d("MainActivity", "Number of recipes: " + recipes.size());
                        recipeAdapter.setRecipes(recipes); // Mettre à jour la liste des recettes dans l'adaptateur
                    }
                } else {

                    Log.e("RecipeSearch", "Erreur lors de la recherche de recettes : " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecipeSearchResponse> call, @NonNull Throwable t) {

                Log.e("RecipeSearch", "Erreur réseau lors de la recherche de recettes : " + t.getMessage());
            }
        });
    }
}
