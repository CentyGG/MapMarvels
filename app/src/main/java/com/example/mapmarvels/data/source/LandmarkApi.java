package com.example.mapmarvels.data.source;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import com.example.mapmarvels.data.dto.LandmarkDto;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LandmarkApi {
    @GET("landmark/{id}")
    Call<LandmarkDto> getById(@Path("id") String id);

    @GET("landmark")
    Call<ArrayList<LandmarkDto>> getAll();

    @POST("landmark")
    Call<Integer> saveLandmark(@Body FullLandmarkEntity landmark);
}
