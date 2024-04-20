package com.example.mapmarvels;

import android.os.Build;

import com.example.mapmarvels.domain.Place;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
    private static final List<Place> places = Arrays.asList(
            new Place(1, "Canada", "good", "https://ibb.co/J7MwBhm",
                    new LatLng(56.130366, -106.346771)),
            new Place(2, "Japan", "bad", "https://ibb.co/J7MwBhm",
                    new LatLng(35.6895, 139.692)));

    public static List<Place> getPlaces() {
        return places;
    }
    public static Place getPlaceByName(String name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return places.stream().filter(p -> p.getName().equals(name)).findAny()
                    .orElseThrow(() -> new RuntimeException("не найдено"));
        }
        else{
            for (Place p:
                    places) {
                if(p.getName().equals(name)) return p;
            }
        }
        return null;
    }

}
