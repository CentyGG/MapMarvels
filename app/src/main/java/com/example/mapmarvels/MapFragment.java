package com.example.mapmarvels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapmarvels.service.MyMapService;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(new MyMapService(getContext()));
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}