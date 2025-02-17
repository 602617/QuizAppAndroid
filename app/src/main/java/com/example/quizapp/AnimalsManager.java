package com.example.quizapp;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalsManager {
    private List<Photo> animalList;

    public AnimalsManager() {
        animalList = new ArrayList<>();
        initializeDefaultAnimals();
    }

    public void initializeDefaultAnimals() {
        // Legg til standarddyr
        animalList.add(new Photo("Tiger", R.drawable.tiger));
        animalList.add(new Photo("Rev", R.drawable.rev));
        animalList.add(new Photo("Gorilla", R.drawable.gorilla));
       // animalList.add(new Photo ("Leo", R.drawable.leo));
    }

    public List<Photo> getAnimalList() {
        return animalList;
    }

    public void addAnimal(String name, int imageResId) {
        animalList.add(new Photo(name, imageResId));
    }

    public void addAnimal(String name, Uri imageUri) {
        animalList.add(new Photo(name,imageUri));
    }

    public void shuffleAnimals() {
        Collections.shuffle(animalList);
    }
    public void deleteItemFromList(Photo photo){
        animalList.remove(photo);
    }
}
