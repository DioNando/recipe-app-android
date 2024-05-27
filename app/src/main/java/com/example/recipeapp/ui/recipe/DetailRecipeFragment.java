package com.example.recipeapp.ui.recipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.text.Html;
import android.text.Spanned;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.Adapters.IngredientAdapter;
import com.example.recipeapp.Listeners.RecipeDetailsListener;
import com.example.recipeapp.LoginActivity;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.R;
import com.example.recipeapp.RequestManager;
import com.example.recipeapp.data.database.FavoriteDatabase;
import com.example.recipeapp.data.entities.Favorite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailRecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int id;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    FloatingActionButton addFavoriteButton;


    RequestManager manager;
    ProgressDialog dialog;
    IngredientAdapter ingredientAdapter;

    public DetailRecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailRecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailRecipeFragment newInstance(String param1, String param2) {
        DetailRecipeFragment fragment = new DetailRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FavoriteDatabase favoriteDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        favoriteDatabase = FavoriteDatabase.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_recipe, container, false);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();



        // Récupérer l'ID depuis le Bundle
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("recipe_id") != null) {

            addFavoriteButton = view.findViewById(R.id.addFavoriteButton);

            id = Integer.parseInt(bundle.getString("recipe_id"));
            manager = new RequestManager(getContext());
            manager.getRecipeDetails(recipeDetailsListener,id);
            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Loading Details...");
            dialog.show();

            // TODO : Click event to add favorite

            addFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String currentDateAndTime = getCurrentDateAndTime();
                    Favorite fav = new Favorite(id, textView_meal_name.getText().toString(), currentDateAndTime);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            favoriteDatabase.favoriteDAO().addFavorite(fav);
                        }
                    }).start();
                    Toast.makeText(getContext(), "Recipe add to favorite", Toast.LENGTH_SHORT).show();
                    addFavoriteButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.md_theme_error)));
                    addFavoriteButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.white)));
                }
            });

        } else {
            Toast.makeText(getContext(), "No recipe id provided", Toast.LENGTH_SHORT).show();
        }

        if (user == null) {
            // TODO : Mask button
            addFavoriteButton.hide();
        }
        findViews(view);
        return view;
    }

    private String getCurrentDateAndTime() {
        // Format de la date et de l'heure
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void findViews(View view) {
        textView_meal_name = view.findViewById(R.id.textView_meal_name);
        textView_meal_source = view.findViewById(R.id.textView_meal_source);
        textView_meal_summary = view.findViewById(R.id.textView_meal_summary);
        imageView_meal_image = view.findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = view.findViewById(R.id.recycler_meal_ingredients);
    }

    private final RecipeDetailsListener recipeDetailsListener =new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            Spanned summary = Html.fromHtml(response.summary, Html.FROM_HTML_MODE_LEGACY);
            textView_meal_summary.setText(summary);


            Picasso.get().load(response.image).into(imageView_meal_image);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_meal_ingredients.getContext(),
                    DividerItemDecoration.VERTICAL);
            recycler_meal_ingredients.addItemDecoration(dividerItemDecoration);
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            ingredientAdapter =new IngredientAdapter(getContext(),response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT).show();
        }
    };
}