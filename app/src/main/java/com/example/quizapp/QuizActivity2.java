package com.example.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity2 extends AppCompatActivity {

    private List<String> randomWords;
    private List<Photo> photoList;
    private int currentIndex = 0;
    private int score = 0;
    private int attempts = 0;
    private TextView scoreText;
    private TextView feedbackTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        randomWords = Arrays.asList(
                "Ku",
                "Sau",
                "Okse",
                "Katt",
                "Hund");
/*
        MyApplication app = (MyApplication) getApplicationContext();
        photoList = app.getPhotoList();

        //unngå duplisering av listen. Lager ny liste hver gang appen lages, eksempel: rotasjon av skjerm.
        if (photoList.isEmpty()) {
            app.addPhoto(new Photo("Tiger", R.drawable.tiger, "Tiger"));
            app.addPhoto(new Photo("Rev", R.drawable.rev, "Rev"));
            app.addPhoto(new Photo("Gorilla", R.drawable.gorilla, "Gorilla"));
            Collections.shuffle(photoList);
        }

*/

        AnimalsManager animalsManager = ((MyApplication) getApplicationContext()).getAnimalsManager();
        photoList = animalsManager.getAnimalList();

        animalsManager.shuffleAnimals();

        /** button to go to next question*/
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(v ->
                displayNextQuestion());

        //displayNextQuestion();

        scoreText = findViewById(R.id.score);
        feedbackTextView = findViewById(R.id.scoreText);

        /*
        * back button finish the activity and returns to mainActivity
        */
        Button btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(v -> {
            finish();
        });

    }



    public void displayNextQuestion() {
        if(currentIndex < photoList.size()) {
            Photo currentPhoto = photoList.get(currentIndex);
            currentIndex++;

            ImageView imageView = findViewById(R.id.imageviev);
            imageView.setImageResource(currentPhoto.getImageResId());

            randomizeButtons(currentPhoto);
        } else {
            //end quiz
            //TextView textView = findViewById(R.id.textOption1);
            //textView.setText("Finished!");
            endQuiz();
        }
    }

    public void randomizeButtons(Photo correctPhoto){
        List<Button> buttons = new ArrayList<>(Arrays.asList(
                findViewById(R.id.button4),
                findViewById(R.id.button2),
                findViewById(R.id.button3)
        ));

        for (Button button : buttons) {
            button.setBackgroundColor(Color.BLUE);
            //fjern tidligere clickListeners
            button.setOnClickListener(null);
        }

        Collections.shuffle(buttons);

        buttons.get(0).setText(correctPhoto.getName());
        buttons.get(0).setOnClickListener(v -> {
            buttons.get(0).setEnabled(false);
            Log.d("BUTTONS", "Correct button pressed ");
            v.setBackgroundColor(Color.GREEN);
            updateScore();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                displayNextQuestion();
                buttons.get(0).setEnabled(true);

            },1000);

        });

        List<String> ranWords = new ArrayList<>(randomWords);
        ranWords.remove(correctPhoto.getName());
        Collections.shuffle(ranWords);

        for (int i = 1; i < buttons.size(); i++) {
            buttons.get(i).setText(ranWords.get(i - 1));
            buttons.get(i).setOnClickListener(v -> {
                Log.d("BUTTONS", "Wrong button pressed");
                v.setBackgroundColor(Color.RED);
                updateScoreMinusOnePoints();
            });
        }
    }

    public void updateScore(){
        score++;
        attempts++;
        scoreText.setText("Score: " + score);
        showFeedback(true);
    }
    public void updateScoreMinusOnePoints(){
        score = Math.max(0, score - 1);
        attempts++;
        scoreText.setText("Score: " + score);
        showFeedback(false);
    }

    public void endQuiz() {
        TextView textView = findViewById(R.id.textOption1);
        textView.setText("You got " + score + " right in " + attempts + " tries - nice job!");
        for (Button button : Arrays.asList(
                findViewById(R.id.button4),
                findViewById(R.id.button2),
                (Button) findViewById(R.id.button3))) {
            button.setEnabled(false); // Disable buttons at the end
        }
    }

    private void showFeedback(boolean isCorrect) {
        feedbackTextView.setVisibility(View.VISIBLE);
        if (isCorrect) {
            feedbackTextView.setText("Correct!");
        }else {
            feedbackTextView.setText("Wrong!");
        }
        // Hide the feedback after a short delay (e.g., 1 second)
        feedbackTextView.postDelayed(() -> feedbackTextView.setVisibility(View.GONE), 1000);
    }



    }
