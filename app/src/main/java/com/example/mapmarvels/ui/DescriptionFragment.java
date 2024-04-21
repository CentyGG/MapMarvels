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
        viewModel = CameraFragment.getViewModelValue();

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