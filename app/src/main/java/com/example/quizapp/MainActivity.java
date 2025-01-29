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

public class MainActivity extends AppCompatActivity {

    private List<Photo> photoList;

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

        Button button = findViewById(R.id.btnStartQuiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity2.class);
                startActivity(intent);
            }
        });

        AnimalsManager animalsManager = ((MyApplication) getApplicationContext()).getAnimalsManager();
        photoList = animalsManager.getAnimalList();
        animalsManager.addAnimal("Sjiraff", R.drawable.sjiraff, "Sjiraff");

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter customAdapter = new CustomAdapter(photoList);
        recyclerView.setAdapter(customAdapter);

        /* input from user*/
        EditText editText = findViewById(R.id.edit_text);
        Button buttonSubmit = findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(v -> {
            String userInput = editText.getText().toString();
            animalsManager.addAnimal(userInput, R.drawable.gorilla, userInput);

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

}