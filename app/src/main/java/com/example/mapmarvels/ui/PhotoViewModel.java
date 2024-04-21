package com.example.mapmarvels.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;

public class PhotoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<File>> images = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> title = new MutableLiveData<>("");
    private MutableLiveData<String> description = new MutableLiveData<>("");
    private MutableLiveData<String> coords = new MutableLiveData<>("");



    public void addImage(File image){
        images.getValue().add(image);
    }

    public ArrayList<File> getImages(){
        return images.getValue();
    }

    public void resetImages(){
        images.getValue().clear();
    }

    public void setTitle(String title){
        this.title.setValue(title);
    }
    public void setDescription(String description){
        this.description.setValue(description);
    }
    public void setCoords(String newCoords){
        coords.setValue(newCoords);
        Log.i("RRR", coords.getValue());
    }
}
