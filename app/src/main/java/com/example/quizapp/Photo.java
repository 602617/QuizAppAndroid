package com.example.quizapp;

public class Photo {
    private String name;
    private int imageResId;
    private String correctAnswer;

    public Photo(String name, int imageResId, String correctAnswer) {
        this.name = name;
        this.imageResId = imageResId;
        this.correctAnswer = correctAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
