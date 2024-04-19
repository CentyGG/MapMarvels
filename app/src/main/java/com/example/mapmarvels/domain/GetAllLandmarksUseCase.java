package com.example.mapmarvels.domain;

import androidx.annotation.NonNull;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;

import java.util.List;
import java.util.function.Consumer;

public class GetAllLandmarksUseCase {
    private final LandmarkRepository repo;

    public GetAllLandmarksUseCase(LandmarkRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<FullLandmarkEntity>>> callback) {
        repo.getAllLandmarks(callback);
    }
}
