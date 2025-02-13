package com.example.quizapp;

import android.app.Application;

import java.util.ArrayList;
/*
* holds a single instance of animalsmanager
* all activities access the same animalsmanager instance
* if list is modified in mainActivity, changes automatically appear in Quizactivity2, or any other activity
* data is lost when the app is closed */
public class MyApplication extends Application {
    private AnimalsManager animalsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        animalsManager = new AnimalsManager();

    }

    public AnimalsManager getAnimalsManager() {
        return animalsManager;
    }


}
