package com.example.mapmarvels.data.source;

import java.util.ArrayList;
import java.util.List;

import com.example.mapmarvels.data.dto.LandmarkDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LandmarkApi {
    @GET("landmark/{id}")
    Call<LandmarkDto> getById(@Path("id") String id);

    @GET("landmark")
    Call<ArrayList<LandmarkDto>> getAll();
}
