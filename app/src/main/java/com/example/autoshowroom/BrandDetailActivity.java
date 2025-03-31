package com.example.autoshowroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BrandDetailActivity extends AppCompatActivity
{
    TextView foundedYearView, countryView, carsCountView, brandNameView;
    ImageView brandLogoView;

    Button seeMoreButton, webButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brand_detail);

        // Initialize views
        brandNameView = findViewById(R.id.brandName);
        foundedYearView = findViewById(R.id.foundedYear);
        countryView = findViewById(R.id.country);
        brandLogoView = findViewById(R.id.brandLogo);
        carsCountView = findViewById(R.id.carsNumber);
        seeMoreButton = findViewById(R.id.seeMoreButton);
        webButton = findViewById(R.id.goToWebButton);
        backButton = findViewById(R.id.backButton);

        // Retrieve data from intent
        Intent intent = getIntent();

        String brandName = intent.getStringExtra("brandName");
        int brandImage = intent.getIntExtra("brandImage", R.drawable.toyota);
        int foundedYear = intent.getIntExtra("foundedYear", 0);
        String country = intent.getStringExtra("country");
        String url = intent.getStringExtra("url");
        int carsCount = intent.getIntExtra("carsCount", 0);

        for(int i = 0; i < carsCount; i++){
            String nameExtraInfo = "car" + i + "name";
            String descriptionExtraInfo = "car" + i + "description";
            String imagePathExtraInfo = "car" + i + "imagePath";


        }

        seeMoreButton.setOnClickListener(v -> {
            Intent intentMore = new Intent(BrandDetailActivity.this, BrandMoreDetailsActivity.class);

            intentMore.putExtra("country", country);

            startActivity(intentMore);
        });

        webButton.setOnClickListener(v -> {
            Intent intentWeb = new Intent(BrandDetailActivity.this, WebInfoActivity.class);
            intentWeb.putExtra("webUrl", url);
            startActivity(intentWeb);
        });



        // Set data
        foundedYearView.setText("Founded year: " + foundedYear);
        countryView.setText("Country: " + country);
        carsCountView.setText("Cars count: " + carsCount);
        brandNameView.setText(brandName);
        brandLogoView.setImageResource(brandImage);

        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}
