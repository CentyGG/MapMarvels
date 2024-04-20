package com.example.mapmarvels.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mapmarvels.R;
import com.example.mapmarvels.databinding.ActivityMainBinding;
import com.example.mapmarvels.service.MyMapService;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();


        BottomNavigationView bottomNavigationView = binding.bottomBar;



    }
}