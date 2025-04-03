package com.example.autoshowroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BrandMoreDetailsActivity extends AppCompatActivity
{
    TextView carsCountView, countryView, car1NameView, car2NameView;
    ImageView car1Image, car2Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_more_details);

        // Initialize views
        carsCountView = findViewById(R.id.carsCount);
        countryView = findViewById(R.id.country);
        car1NameView = findViewById(R.id.car1Name);
        car1Image = findViewById(R.id.car1Image);
        car2NameView = findViewById(R.id.car2Name);
        car2Image = findViewById(R.id.car2Image);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Get data from intent
        Intent intent = getIntent();
        int carsCount = intent.getIntExtra("carsCount", 0);
        String country = intent.getStringExtra("country");
        carsCountView.setText("Cars count:" + carsCount);
        countryView.setText("Country: " + country);

        for(int i = 0; i < 2; i++){
            String nameExtraInfo = "car" + i + "name";
            String descriptionExtraInfo = "car" + i + "description";
            String imagePathExtraInfo = "car" + i + "imagePath";

            String name = intent.getStringExtra(nameExtraInfo);
            String description = intent.getStringExtra(descriptionExtraInfo);
            String imagePath = intent.getStringExtra(imagePathExtraInfo);

            if(i == 0){
                car1NameView.setText(name);
                int carImage = this.getResources().getIdentifier(imagePath, "drawable", this.getPackageName());
                car1Image.setImageResource(carImage);
            } else {
                car2NameView.setText(name);
                int carImage = this.getResources().getIdentifier(imagePath, "drawable", this.getPackageName());
                car2Image.setImageResource(carImage);
            }
        }
    }

}
