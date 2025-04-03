package com.example.autoshowroomapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button buttonViewPlayers, buttonManagePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonViewPlayers = findViewById(R.id.buttonViewCars);
        buttonManagePlayers = findViewById(R.id.buttonManagePlayers);

        buttonViewPlayers.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        buttonManagePlayers.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ManageCarsActivity.class);
            startActivity(intent);
        });
    }
}
