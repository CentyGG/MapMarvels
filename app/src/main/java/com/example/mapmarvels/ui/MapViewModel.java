package com.example.mapmarvels.ui;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mapmarvels.data.LandmarkRepositoryImpl;
import com.example.mapmarvels.domain.GetAllLandmarksUseCase;
import com.example.mapmarvels.domain.entites.FullLandmarkEntity;
import com.example.mapmarvels.domain.entites.Status;


import java.util.List;

public class MapViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final GetAllLandmarksUseCase getAllLandmarksUseCase = new GetAllLandmarksUseCase(
            LandmarkRepositoryImpl.getInstance()
    );

    public MapViewModel() {
        update();
    }

    public void update() {
        mutableStateLiveData.setValue(new State(null, null, true));
        getAllLandmarksUseCase.execute(status -> {
            mutableStateLiveData.postValue(fromStatus(status));
        });
    }

    private State fromStatus(Status<List<FullLandmarkEntity>> status) {
        return new State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }

    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final List<FullLandmarkEntity> items;

        private final boolean isLoading;

        public State(@Nullable String errorMessage, @Nullable List<FullLandmarkEntity> items, boolean isLoading) {
            this.errorMessage = errorMessage;
            this.items = items;
            this.isLoading = isLoading;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<FullLandmarkEntity> getItems() {
            return items;
        }

        public boolean isLoading() {
            return isLoading;
        }
    }
}
