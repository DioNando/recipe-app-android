package com.example.recipeapp.ui.recipe;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.RecipeAdapter;
import com.example.recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.Models.RandomRecipeApiResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.databinding.FragmentRecipeBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {

    private FragmentRecipeBinding binding;
    private ProgressDialog dialog;
    private RequestManager manager;
    private Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView searchView;
    private RecyclerView recyclerView1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dialog = new ProgressDialog(requireContext());
        dialog.setTitle("Loading...");
        searchView = root.findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner = root.findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
        manager = new RequestManager(requireContext());
        manager.getRandomRecipes(randomRecipeResponseListener,tags);
        dialog.show();

        recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            //randomRecipeAdapter = new RandomRecipeAdapter(requireContext(), response.recipes);
            //recyclerView.setAdapter(randomRecipeAdapter);

            RecipeAdapter adapterRecipe = new RecipeAdapter(requireContext(), response.recipes, recipeClickListener);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView1.setLayoutManager(layoutManager1);
            recyclerView1.setHasFixedSize(true);
            recyclerView1.setAdapter(adapterRecipe);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    };
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // No implementation
        }
    };


    private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("recipe_id", id);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_nav_recipe_to_nav_detail_recipe, bundle);

        }
    };
}