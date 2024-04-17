package com.example.recipeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.recipeapp.data.Recipe;
import com.example.recipeapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_ingredient, R.id.nav_recipe, R.id.nav_favorite, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Initialisez Retrofit
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
                        // Récupérez les recettes et traitez-les ici
                        List<Recipe> recipes = recipeSearchResponse.getRecipes();
                        // Par exemple, vous pouvez afficher le titre de la première recette
                        if (!recipes.isEmpty()) {
                            Recipe firstRecipe = recipes.get(0);
                            String firstRecipeTitle = firstRecipe.getTitle();
                            // Affichez le titre dans un toast ou dans un élément de votre interface utilisateur
                            Toast.makeText(MainActivity.this, "First recipe: " + firstRecipeTitle, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Gérer les erreurs de réponse de l'API
                    // Par exemple, afficher un message d'erreur
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecipeSearchResponse> call, @NonNull Throwable t) {
                // Gérer les erreurs de réseau
                // Par exemple, afficher un message d'erreur
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

