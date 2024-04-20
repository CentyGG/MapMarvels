package com.example.mapmarvels.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;

public class PhotoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<File>> images = new MutableLiveData<>(new ArrayList<>());

    public void addImage(File image){
        images.getValue().add(image);
    }

    public ArrayList<File> getImages(){
        return images.getValue();
    }

    public void resetImages(){
        images.getValue().clear();
    }
}
