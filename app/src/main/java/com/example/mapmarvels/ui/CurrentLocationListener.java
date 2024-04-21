package com.example.mapmarvels.ui;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

public class CurrentLocationListener implements LocationListener {
    private PhotoViewModel viewModel;
    private LocationManager locationManager;
    private Context context;

    public CurrentLocationListener(PhotoViewModel viewModel, Context context){
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String coords = latitude +", "+ longitude;
        // Теперь передаем координаты во ViewModel
        viewModel.setCoords(coords);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
