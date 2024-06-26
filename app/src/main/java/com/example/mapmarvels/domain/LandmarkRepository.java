package com.example.mapmarvels.domain;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;

public interface LandmarkRepository {

    void getLandmark(@NonNull String id, @NonNull Consumer<Status<FullLandmarkEntity>> callback);
    void getAllLandmarks(@NonNull Consumer<Status<List<FullLandmarkEntity>>> callback);
    void saveLandmark(@NonNull String title, @NonNull String description, @NonNull String image, @NonNull String coords, @NonNull Consumer<Status<Integer>> callback);
}
