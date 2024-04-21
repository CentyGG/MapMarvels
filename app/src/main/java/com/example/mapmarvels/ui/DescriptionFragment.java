package com.example.mapmarvels.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.http.UploadDataSink;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mapmarvels.R;
import com.example.mapmarvels.databinding.FragmentDescriptionBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DescriptionFragment extends Fragment implements LocationListener {
    private FragmentDescriptionBinding binding;

    private ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                if (isGranted.get(Manifest.permission.ACCESS_FINE_LOCATION) == true) {
                    Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            });
    private PhotoViewModel viewModel;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            viewModel.setCoords(location.getLatitude() + ", " + location.getLongitude());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        viewModel = CameraFragment.getViewModelValue();

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        // LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);










        // locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, Looper.getMainLooper());

        binding.save.setOnClickListener(v -> {
            String name = binding.addName.getText().toString();
            String desc = binding.addDesc.getText().toString();
            viewModel.setTitle(name);
            viewModel.setDescription(desc);


            // Запрашиваем обновление местоположения один раз
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://mapmarvels.appspot.com");////////////////////////////////
            // Create a storage reference from our app
            StorageReference storageRef = firebaseStorage.getReference();

            ArrayList<File> images = viewModel.getImages();
            for (File f : images) {
                Uri file = Uri.fromFile(f);
                StorageReference riversRef = storageRef.child("images/" + file.getLastPathSegment());
                UploadTask uploadTask = riversRef.putFile(file);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                    }
                });
            }


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

    private void checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION )== PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissionLauncher.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});

        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(requireContext(), "Локация считана", Toast.LENGTH_LONG).show();
        viewModel.setCoords(location.getLatitude()+ ", " + location.getLongitude());
    }
}