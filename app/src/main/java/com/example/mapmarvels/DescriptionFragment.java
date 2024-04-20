package com.example.mapmarvels;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapmarvels.databinding.FragmentDescriptionBinding;

public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(getLayoutInflater());
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        binding.save.setOnClickListener(v -> {
            String name = binding.addName.getText().toString();
            String description = binding.addDesc.getText().toString();
            navController.navigate(R.id.action_descFragment_to_cameraFragment);
        });
        return binding.getRoot();
    }
}
