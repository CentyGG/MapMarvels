package com.example.mapmarvels.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.example.mapmarvels.R;
import com.example.mapmarvels.databinding.FragmentDescriptionBinding;
import com.google.android.gms.location.LocationCallback;

public class DescriptionFragment extends Fragment implements LocationListener {
    private FragmentDescriptionBinding binding;
    private ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                if (isGranted.get(Manifest.permission.ACCESS_FINE_LOCATION) == true) {
                    Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    getLocation();
                }
            });
    private PhotoViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        checkLocationPermission();
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
//        viewModel.coords.observe(getViewLifecycleOwner(), observer -> {
//            Toast.makeText(requireContext(), viewModel.coords.getValue(), Toast.LENGTH_LONG).show();
//        });


        binding.save.setOnClickListener(v -> {


            // Запрашиваем обновление местоположения один раз


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

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissionLauncher.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});

        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(requireContext(), location.getLatitude()+ ", " + location.getLongitude(), Toast.LENGTH_LONG).show();
        viewModel.setCoords(location.getLatitude()+ ", " + location.getLongitude());
    }
    private void getLocation(){
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        @SuppressLint("MissingPermission") android.location.Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        double userLat = lastKnownLocation.getLatitude();
        double userLong = lastKnownLocation.getLongitude();
        viewModel.setCoords(userLat +", " + userLong);
    }
}