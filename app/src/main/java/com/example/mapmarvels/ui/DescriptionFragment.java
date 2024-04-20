package com.example.mapmarvels.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mapmarvels.R;
import com.example.mapmarvels.databinding.FragmentDescriptionBinding;

import java.io.File;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;
    private PhotoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);





        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        binding.save.setOnClickListener(v -> {
            String name = binding.addName.getText().toString();
            String description = binding.addDesc.getText().toString();


            ///
            ////
            ////
            ////
            ////передача данных на сервак

            viewModel.resetImages();
            navController.navigate(R.id.action_descFragment_to_cameraFragment);
        });

        return binding.getRoot();
    }
}