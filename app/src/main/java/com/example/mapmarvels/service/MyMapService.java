package com.example.mapmarvels.service;

import static android.graphics.Color.TRANSPARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mapmarvels.DB;
import com.example.mapmarvels.R;
import com.example.mapmarvels.domain.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class MyMapService implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    private  final Context context;

    public MyMapService(Context context) {
        this.context = context;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Toast.makeText(context, latLng.latitude + " " + latLng.longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Toast.makeText(context, latLng.latitude + " " + latLng.longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        for (Place p: DB.getPlaces()){
            googleMap.addMarker(new MarkerOptions().position(p.getLatLng()).title(p.getName()));
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Place place = DB.getPlaceByName(marker.getTitle());
                BottomSheetDialog dialog = new BottomSheetDialog(context);

                dialog.setContentView(R.layout.map_dialog_fragment);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
                dialog.show();


                return false;
            }
        });

    }
}
