package com.example.mapmarvels.domain.entites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FullLandmarkEntity {
    @NonNull
    private final Integer id;
    @NonNull
    private final String title;
    @Nullable
    private final String description;
    @Nullable
    private final String image;
    @Nullable
    private final String coords;

    public FullLandmarkEntity(
            @NonNull Integer id,
            @NonNull String title,
            @Nullable String description,
            @Nullable String image,
            @Nullable String coords
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.coords = coords;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Nullable
    public String getCoords() {
        return coords;
    }
}
