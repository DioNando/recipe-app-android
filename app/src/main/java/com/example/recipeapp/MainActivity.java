package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.databinding.FragmentProfileBinding;
import com.example.recipeapp.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipeapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    FirebaseUser user;

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

        // Trouver le bouton "logout" dans le menu de navigation
        MenuItem logoutMenuItem = navigationView.getMenu().findItem(R.id.btn_logout);
        // Ajouter un écouteur de clic au bouton "logout"
        logoutMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Appeler la méthode logout lorsque le bouton "logout" est cliqué
                logout();
                return true;
            }
        });
        //verifier si un user est deja connecter
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            // Récupérer le layout du nav_header
            View headerView = navigationView.getHeaderView(0);
            // Récupérer la TextView où afficher l'adresse e-mail de l'utilisateur
            TextView nameTextView = headerView.findViewById(R.id.textViewUsername);
            TextView emailTextView = headerView.findViewById(R.id.textView);
            nameTextView.setText(user.getDisplayName());
            // Définir le texte de la TextView avec l'adresse e-mail de l'utilisateur
            emailTextView.setText(user.getEmail());
            // Afficher le bouton de déconnexion
            logoutMenuItem.setVisible(true);
        }else {
            // Masquer le bouton de déconnexion
            logoutMenuItem.setVisible(false);
        }

        if (getIntent().getBooleanExtra("navigateToHome", false)) {
            navigateToHomeFragment();
        }
    }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.main, menu);
    //  return true;
    //}

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Méthode pour gérer la déconnexion de l'utilisateur
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Successful Logout ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //Methode qui permet de naviguer vers le homefragment sans etre connecter
    public void navigateToHomeFragment() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_home);
    }



}
