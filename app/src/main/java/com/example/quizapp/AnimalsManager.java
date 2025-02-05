package com.example.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalsManager {
    private List<Photo> animalList;

    public AnimalsManager() {
        animalList = new ArrayList<>();
        initializeDefaultAnimals();
    }

    private void initializeDefaultAnimals() {
        // Legg til standarddyr
        animalList.add(new Photo("Tiger", R.drawable.tiger, "Tiger"));
        animalList.add(new Photo("Rev", R.drawable.rev, "Rev"));
        animalList.add(new Photo("Gorilla", R.drawable.gorilla, "Gorilla"));
        animalList.add(new Photo ("Leo", R.drawable.leo, "Leo"));
    }

    public List<Photo> getAnimalList() {
        return animalList;
    }

    public void addAnimal(String name, int imageResId, String description) {
        animalList.add(new Photo(name, imageResId, description));
    }

    public void shuffleAnimals() {
        Collections.shuffle(animalList);
    }

    public void clearAnimals() {
        animalList.clear();
    }
    public void deleteItemFromList(Photo photo){
        animalList.remove(photo);
    }
}
