package com.example.quizapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private ArrayList<Photo> photoList = new ArrayList<>();


    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public void addPhoto(Photo photo) {
        photoList.add(photo);
    }


}
