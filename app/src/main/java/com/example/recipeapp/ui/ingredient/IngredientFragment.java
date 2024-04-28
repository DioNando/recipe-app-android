package com.example.recipeapp.ui.ingredient;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.IngredientsAdapter;
import com.example.recipeapp.Listeners.RecipeDetailsListener;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.databinding.FragmentIngredientBinding;
import com.squareup.picasso.Picasso;

public class IngredientFragment extends Fragment {
    int id;
    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    private FragmentIngredientBinding binding;

    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("recipe_id") != null) {
            id = Integer.parseInt(bundle.getString("recipe_id"));
            manager = new RequestManager(getContext());
            manager.getRecipeDetails(recipeDetailsListener,id);
            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Loading Details...");
            dialog.show();
        } else {
            Toast.makeText(getContext(), "No recipe id provided", Toast.LENGTH_SHORT).show();
        }
        findViews(root);
        return root;
    }

    private void findViews(View root) {
        textView_meal_name = root.findViewById(R.id.textView_meal_name);
        textView_meal_source = root.findViewById(R.id.textView_meal_source);
        textView_meal_summary = root.findViewById(R.id.textView_meal_summary);
        imageView_meal_image = root.findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = root.findViewById(R.id.recycler_meal_ingredients);
    }

private final RecipeDetailsListener recipeDetailsListener =new RecipeDetailsListener() {
    @Override
    public void didFetch(RecipeDetailsResponse response, String message) {
       dialog.dismiss();
       textView_meal_name.setText(response.title);
       textView_meal_source.setText(response.sourceName);
       textView_meal_summary.setText(response.summary);
        Picasso.get().load(response.image).into(imageView_meal_image);
recycler_meal_ingredients.setHasFixedSize(true);
recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
ingredientsAdapter =new IngredientsAdapter(getContext(),response.extendedIngredients);
recycler_meal_ingredients.setAdapter(ingredientsAdapter);

    }

    @Override
    public void didError(String message) {
        Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT).show();
    }
};
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}