package com.example.autoshowroomapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CarDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView modelText, brandText, ageText, mileageText, fuelTypeText, colorText;
    private Button buttonMoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        imageView = findViewById(R.id.imageCarDetail);
        modelText = findViewById(R.id.textModelDetail);
        brandText = findViewById(R.id.textBrandDetail);
        ageText = findViewById(R.id.textAgeDetail);
        mileageText = findViewById(R.id.textMileageDetail);
        fuelTypeText = findViewById(R.id.textFuelTypeDetail);
        colorText = findViewById(R.id.textColorDetail);
        buttonMoreDetails = findViewById(R.id.buttonMoreDetails);

        Car car = (Car) getIntent().getSerializableExtra("car");

        if (car != null) {
            modelText.setText(car.modelName);
            brandText.setText(car.brandName);
            ageText.setText(String.valueOf(car.age));
            mileageText.setText(String.valueOf(car.mileage));
            fuelTypeText.setText(car.fuelType);
            colorText.setText(car.color);

            Glide.with(this)
                    .load(car.imageUrl)
                    .into(imageView);

            buttonMoreDetails.setOnClickListener(v -> {
                Intent intent = new Intent(this, CarMoreDetailsActivity.class);
                intent.putExtra("car", car);
                startActivity(intent);
            });
        }
    }
}
