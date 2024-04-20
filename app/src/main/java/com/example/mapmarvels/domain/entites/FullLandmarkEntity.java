package com.example.mapmarvels.domain.entites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FullLandmarkEntity {
    @NonNull
    private final String id;
    @NonNull
    private final String title;
    @Nullable
    private final String description;
    @Nullable
    private final byte[] image;
    @Nullable
    private final String coords;

    public FullLandmarkEntity(
            @NonNull String id,
            @NonNull String title,
            @Nullable String description,
            @Nullable byte[] image,
            @Nullable String coords
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.coords = coords;
    }

    @NonNull
    public String getId() {
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
    public byte[] getImage() {
        return image;
    }

    @Nullable
    public String getCoords() {
        return coords;
    }
}
