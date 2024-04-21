package com.example.mapmarvels.ui;

import android.os.Bundle;

import android.util.Log;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import com.example.mapmarvels.R;
import com.example.mapmarvels.service.MyMapService;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {

    private MapViewModel viewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        viewModel = new ViewModelProvider(this).get(MapViewModel.class);

        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            if (state.getErrorMessage()!=null){
                Log.i("RRR", state.getErrorMessage());
            }
            boolean isSuccess = !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getItems() != null;
            if (isSuccess){
                Log.i("RRR", "state.getItems().get(0).getCoords()");
                mapFragment.getMapAsync(new MyMapService(getContext(), state.getItems()));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.update();
    }
}