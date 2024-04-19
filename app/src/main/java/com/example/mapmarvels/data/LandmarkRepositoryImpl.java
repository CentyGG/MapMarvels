package com.example.mapmarvels.data;

import androidx.annotation.NonNull;
import com.example.mapmarvels.data.dto.LandmarkDto;
import com.example.mapmarvels.data.network.RetrofitFactory;
import com.example.mapmarvels.data.source.LandmarkApi;
import com.example.mapmarvels.data.utils.CallToConsumer;
import com.example.mapmarvels.domain.LandmarkRepository;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class LandmarkRepositoryImpl implements LandmarkRepository {
    private static LandmarkRepositoryImpl INSTANCE;
    private final LandmarkApi landmarkApi = RetrofitFactory.getInstance().getLandmarkApi();

    private LandmarkRepositoryImpl() {}

    public static synchronized LandmarkRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LandmarkRepositoryImpl();
        }
        return INSTANCE;
    }



    @Override
    public void getLandmark(@NonNull String id, @NonNull Consumer<Status<FullLandmarkEntity>> callback) {
        landmarkApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                landmark -> {
                    final String resultId = landmark.id;
                    final String title = landmark.title;
                    final String description = landmark.description;
                    final byte[] image = landmark.image;
                    final String coords = landmark.coords;
                    if (resultId != null && title != null && description != null && image != null && coords != null) {
                        return new FullLandmarkEntity(
                                resultId,
                                title,
                                description,
                                image,
                                coords
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void getAllLandmarks(@NonNull @NotNull Consumer<Status<List<FullLandmarkEntity>>> callback) {
        landmarkApi.getAll().enqueue(new CallToConsumer<>(
                callback,
                landmarksDto -> {
                    ArrayList<FullLandmarkEntity> result = new ArrayList<>(landmarksDto.size());
                    for (LandmarkDto landmark : landmarksDto) {
                        final String id = landmark.id;
                        final String title = landmark.title;
                        final String description = landmark.description;
                        final byte[] image = landmark.image;
                        final String coords = landmark.coords;
                        if (id != null && title != null && description != null && image != null && coords != null) {
                            result.add(new FullLandmarkEntity(id, title, description, image, coords));
                        }
                    }
                    return result;
                }
        ));
    }


}
