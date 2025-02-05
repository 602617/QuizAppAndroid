package com.example.quizapp;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.CustomAdapter.OnPhotoClickListener;
import com.example.quizapp.Photo;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnPhotoClickListener {

    private List<Photo> photoList;
    private AnimalsManager animalsManager;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /** Button to start the quiz.
         * adds clicklistener that takes you to quiz activity*/
        Button button = findViewById(R.id.btnStartQuiz);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity2.class);
            startActivity(intent);
        });

        animalsManager = ((MyApplication) getApplicationContext()).getAnimalsManager();
        photoList = animalsManager.getAnimalList();
        animalsManager.addAnimal("Sjiraff", R.drawable.sjiraff, "Sjiraff");

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customAdapter = new CustomAdapter(photoList,  this);
        recyclerView.setAdapter(customAdapter);

        /* text input from user*/
        EditText editText = findViewById(R.id.edit_text);
        Button buttonSubmit = findViewById(R.id.button_submit);

        /* image input from user*/


        buttonSubmit.setOnClickListener(v -> {
            String userInput = editText.getText().toString().trim();
            String cap = userInput.substring(0,1).toUpperCase() + userInput.substring(1);
            animalsManager.addAnimal(cap, R.drawable.gorilla, cap);

            customAdapter.notifyDataSetChanged();
        });

        /* sort the list in recycleView*/
        Button buttonSortAZ = findViewById(R.id.btnSortAZ);
        Button buttonSortZA = findViewById(R.id.btnSortZA);

        buttonSortAZ.setOnClickListener(v -> {
            Collections.sort(photoList, Comparator.comparing(Photo::getName));
            customAdapter.notifyDataSetChanged();
        });
        buttonSortZA.setOnClickListener(v -> {
            Collections.sort(photoList, (p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()));
            customAdapter.notifyDataSetChanged();
        });


    }

    /**
     * uses the interface in CustomAdapter that handles click on image.
     * deletes the image from the list
     */
    @Override
    public void onPhotoClick(Photo photo) {
        Toast.makeText(this,"Clicked: " + photo.getName(), Toast.LENGTH_SHORT).show();
        animalsManager.deleteItemFromList(photo);
        customAdapter.notifyDataSetChanged();
        // can use notifyItemRemoved to update only the deleted item
    }


}