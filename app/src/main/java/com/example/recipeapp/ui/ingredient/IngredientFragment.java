package com.example.recipeapp.ui.ingredient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.IngredientAdapter;
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
        // IngredientAdapter adapterIngredient = new IngredientAdapter();
        // recyclerView2.setAdapter(adapterIngredient);

        binding.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_ingredient_to_nav_detail_ingredient);
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