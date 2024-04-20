package com.example.mapmarvels.service;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.mapmarvels.R;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import static android.graphics.Color.TRANSPARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MyMapService implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    private  final Context context;
    private final List<FullLandmarkEntity> items;

    public MyMapService(Context context, List<FullLandmarkEntity> items) {
        this.context = context;
        this.items = items;
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
        if (items != null){
            Log.i("RRR", items.get(0).getCoords());
        }



        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        for (FullLandmarkEntity p: items){
            LatLng coords = stringToLatLng(p.getCoords());
            googleMap.addMarker(new MarkerOptions().position(coords).title(p.getTitle()));
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                FullLandmarkEntity place = getPlaceByName(marker.getTitle());
                BottomSheetDialog dialog = new BottomSheetDialog(context);


                dialog.setContentView(R.layout.map_dialog_fragment);
                MaterialTextView textViewName = dialog.findViewById(R.id.tv_name);
                if (textViewName != null) {
                    textViewName.setText(place.getTitle());
                }

                TextView textViewDescription = dialog.findViewById(R.id.tv_description);
                if (textViewDescription != null) {
                    textViewDescription.setText(place.getDescription());
                }

                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
                dialog.show();


                return false;
            }
        });

    }
    private LatLng stringToLatLng(String coords){
        String[] parts = coords.split(",");
        double latitude = Double.parseDouble(parts[0]);
        double longitude = Double.parseDouble(parts[1]);

        LatLng latLng = new LatLng(latitude, longitude);
        return latLng;
    }

    public FullLandmarkEntity getPlaceByName(String name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return items.stream().filter(p -> p.getTitle().equals(name)).findAny()
                    .orElseThrow(() -> new RuntimeException("не найдено"));
        }
        else{
            for (FullLandmarkEntity p:
                    items) {
                if(p.getTitle().equals(name)) return p;
            }
        }
        return null;
    }
}
