package com.example.recipeapp.ui.favorite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeapp.data.entities.Favorite;
import com.example.recipeapp.data.dao.FavoriteDAO;
import com.example.recipeapp.data.database.FavoriteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteViewModel extends AndroidViewModel {

    private final FavoriteDAO favoriteDAO;
    private final ExecutorService executorService;
    private final MutableLiveData<List<Favorite>> favoritesLiveData;

    public FavoriteViewModel(Application application) {
        super(application);
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDAO = database.favoriteDAO();
        executorService = Executors.newSingleThreadExecutor();
        favoritesLiveData = new MutableLiveData<>();
        loadFavorites();
    }

    public LiveData<List<Favorite>> getFavorites() {
        return favoritesLiveData;
    }

    public void deleteFavorite(String id) {
        executorService.execute(() -> {
            favoriteDAO.deleteFavorite(id);
            loadFavorites();
        });
    }

    private void loadFavorites() {
        executorService.execute(() -> {
            List<Favorite> favorites = favoriteDAO.getAllFavorites();
            favoritesLiveData.postValue(favorites);
        });
    }
}
