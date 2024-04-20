package com.example.mapmarvels.domain;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Place {
    private final long id;

    private final String name;

    private final String description;

    private final String photoes;

    private final LatLng latLng;

    public Place(long id, String name, String description,String photoes, LatLng latLng) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoes = photoes;
        this.latLng = latLng;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoes() {
        return photoes;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
