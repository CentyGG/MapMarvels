package com.example.mapmarvels;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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
        binding.save.setOnClickListener(v -> {
            String name = binding.addName.getText().toString();
            String description = binding.addDesc.getText().toString();
        });
        return binding.getRoot();
    }
}
