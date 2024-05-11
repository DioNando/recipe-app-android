package com.example.recipeapp.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.RecipeAdapter;
import com.example.recipeapp.R;
import com.example.recipeapp.databinding.FragmentRecipeBinding;

public class RecipeFragment extends Fragment {

    private FragmentRecipeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewRecipe);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        RecipeAdapter adapterRecipe = new RecipeAdapter();
        recyclerView1.setAdapter(adapterRecipe);

        binding.btnDetailRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_recipe_to_nav_detail_recipe);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}