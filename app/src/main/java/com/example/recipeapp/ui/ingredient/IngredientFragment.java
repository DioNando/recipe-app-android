package com.example.recipeapp.ui.ingredient;

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

import com.example.recipeapp.Adapters.IngredientAdapter;
import com.example.recipeapp.Adapters.IngredientPageAdapter;
import com.example.recipeapp.Listeners.FavoriteClickListener;
import com.example.recipeapp.Listeners.IngredientClickListener;
import com.example.recipeapp.R;
import com.example.recipeapp.databinding.FragmentIngredientBinding;

public class IngredientFragment extends Fragment {

    private FragmentIngredientBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerViewIngredient);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        IngredientPageAdapter adapterPageIngredient = new IngredientPageAdapter(ingredientClickListener);
        recyclerView2.setAdapter(adapterPageIngredient);
        

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private final IngredientClickListener ingredientClickListener= new IngredientClickListener() {
        @Override
        public void onIngredientClicked(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("recipe_id", id);
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_nav_ingredient_to_nav_detail_ingredient, bundle);

        }
    };
}