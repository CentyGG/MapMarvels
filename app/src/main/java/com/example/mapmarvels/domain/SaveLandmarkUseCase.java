package com.example.mapmarvels.domain;

import androidx.annotation.NonNull;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;

import java.util.function.Consumer;

public class SaveLandmarkUseCase {
    private final LandmarkRepository repo;

    public SaveLandmarkUseCase(LandmarkRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String title, @NonNull String description, @NonNull byte[] image, @NonNull String coords, @NonNull Consumer<Status<Integer>> callback) {
        repo.saveLandmark(title, description, image, coords, callback);
    }
}
