package com.example.mapmarvels.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mapmarvels.data.LandmarkRepositoryImpl;
import com.example.mapmarvels.domain.GetAllLandmarksUseCase;
import com.example.mapmarvels.domain.SaveLandmarkUseCase;

import java.io.File;
import java.util.ArrayList;

public class PhotoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<File>> images = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> imagesUrl= new MutableLiveData<>(new String());
    private MutableLiveData<String> title = new MutableLiveData<>("");
    private MutableLiveData<String> description = new MutableLiveData<>("");
    public  MutableLiveData<String> coords = new MutableLiveData<>("");

    private final SaveLandmarkUseCase saveLandmarkUseCase = new SaveLandmarkUseCase(
            LandmarkRepositoryImpl.getInstance()
    );

    public void addImage(File image){
        images.getValue().add(image);
    }

    public ArrayList<File> getImages(){
        return images.getValue();
    }

    public void resetImages(){
        images.getValue().clear();
    }
    public void resetImagesUrl(){
        imagesUrl.setValue("");
    }

    public void setTitle(String title){
        this.title.setValue(title);
    }

    public void setImagesUrl(String imagesUrl){
        this.imagesUrl.setValue(imagesUrl);
    }
    public void setDescription(String description){
        this.description.setValue(description);
    }
    public void setCoords(String newCoords){
        coords.setValue(newCoords);
        Log.i("RRR", coords.getValue());
    }

    public void saveData(){
        saveLandmarkUseCase.execute(title.getValue(), description.getValue(), imagesUrl.getValue(), coords.getValue(), callback -> {
            resetImages();
            resetImagesUrl();
        });
    }
}
