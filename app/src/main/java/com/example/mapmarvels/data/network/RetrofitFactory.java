package com.example.mapmarvels.data.network;

import com.example.mapmarvels.data.source.LandmarkApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {}

    public static synchronized RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.100:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LandmarkApi getLandmarkApi() {
        return retrofit.create(LandmarkApi.class);
    }
}
