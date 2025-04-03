package com.example.autoshowroomapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarMoreDetailsActivity extends AppCompatActivity {

    private TextView textModel, textBrand, textNumber, textFuelType, textAge, textMileage, textColor;
    private Button buttonOpenWiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_more_details);

        textModel = findViewById(R.id.textMoreModel);
        textBrand = findViewById(R.id.textMoreBrand);
        textNumber = findViewById(R.id.textMoreNumber);
        textFuelType = findViewById(R.id.textMoreFuelType);
        textAge = findViewById(R.id.textMoreAge);
        textMileage = findViewById(R.id.textMoreMileage);
        textColor = findViewById(R.id.textMoreColor);

        buttonOpenWiki = findViewById(R.id.buttonOpenWiki);

        Car car = (Car) getIntent().getSerializableExtra("car");

        if (car != null) {
            textModel.setText(car.modelName);
            textBrand.setText(car.brandName);
            textNumber.setText(String.valueOf(car.number));
            textFuelType.setText(car.fuelType);
            textAge.setText(String.valueOf(car.age));
            textMileage.setText(String.valueOf(car.mileage));
            textColor.setText(car.color);
        }

        // Open Wikipedia URL in a WebView activity
        buttonOpenWiki.setOnClickListener(v -> {
            Intent intent = new Intent(CarMoreDetailsActivity.this, CarWebActivity.class);
            intent.putExtra("wikipediaUrl", car.wikipediaUrl);
            startActivity(intent);
        });
    }
}
