package com.example.autoshowroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class BrandMoreDetailsActivity extends AppCompatActivity {
    TextView carsCountView, countryView;
    RecyclerView carRecyclerView;
    CarAdapter carAdapter;
    List<CarModel> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_more_details);

        // Initialize fixed views
        carsCountView = findViewById(R.id.carsCount);
        countryView = findViewById(R.id.country);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Retrieve data from the Intent
        Intent intent = getIntent();
        int carsCount = intent.getIntExtra("carsCount", 0);
        String country = intent.getStringExtra("country");
        carsCountView.setText("Cars count: " + carsCount);
        countryView.setText("Country: " + country);

        // Create the list of CarModel objects based on the intent extras
        carList = new ArrayList<>();
        for (int i = 0; i < carsCount; i++) {
            String nameExtra = "car" + i + "name";
            String imageExtra = "car" + i + "imagePath";
            String descriptionExtra = "car" + i + "description";

            String carName = intent.getStringExtra(nameExtra);
            String imagePath = intent.getStringExtra(imageExtra);
            String description = intent.getStringExtra(descriptionExtra);

            // Check for non-null values (optional)
            if (carName != null && imagePath != null && description != null) {
                carList.add(new CarModel(carName, imagePath, description));
            }
        }

        // Set up the RecyclerView with a LinearLayoutManager and the adapter
        carRecyclerView = findViewById(R.id.carRecyclerView);
        carRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carAdapter = new CarAdapter(this, carList);
        carRecyclerView.setAdapter(carAdapter);
    }
}
