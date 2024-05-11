package com.example.recipeapp.ui.ingredient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.R;
import com.example.recipeapp.databinding.FragmentDetailIngredientBinding;
import com.example.recipeapp.databinding.FragmentIngredientBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailIngredientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailIngredientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailIngredientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailIngredientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailIngredientFragment newInstance(String param1, String param2) {
        DetailIngredientFragment fragment = new DetailIngredientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentDetailIngredientBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDetailIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnDetailsRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_detail_ingredient_to_nav_detail_recipe);
            }
        });

        return root;
        // return inflater.inflate(R.layout.fragment_detail_ingredient, container, false);
    }
}