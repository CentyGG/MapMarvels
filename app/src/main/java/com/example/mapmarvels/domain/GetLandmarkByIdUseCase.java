package com.example.mapmarvels.domain;

import androidx.annotation.NonNull;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;

import java.util.function.Consumer;

public class GetLandmarkByIdUseCase {
    private final LandmarkRepository repo;

    public GetLandmarkByIdUseCase(LandmarkRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullLandmarkEntity>> callback) {
        repo.getLandmark(id, callback);
    }
}
