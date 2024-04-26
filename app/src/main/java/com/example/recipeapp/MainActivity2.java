package com.example.recipeapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapters.RandomRecipeAdapter;
import com.example.recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.recipeapp.Models.RandomRecipeApiResponse;

public class MainActivity2 extends AppCompatActivity {

     ProgressDialog dialog;
     RequestManager manager;

     RandomRecipeAdapter randomRecipeAdapter;
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main2);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        manager = new RequestManager(this);
        manager.getRandomRecipies(randomRecipeResponseListener);
        dialog.show();

    }

        private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
            @Override
            public void didFetch(RandomRecipeApiResponse response, String message) {
                dialog.dismiss();
            recyclerView=findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL,false));
            randomRecipeAdapter=new RandomRecipeAdapter(MainActivity2.this,response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
            }

            @Override
            public void didError(String message) {
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
            }
        };



}
