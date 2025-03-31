package com.example.autoshowroom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.entities.CarBrand;
import com.example.autoshowroom.entities.CarModel;
import com.example.autoshowroom.utils.XMLCarBrandParser;

import java.util.List;

public class BrandActivity extends AppCompatActivity implements CarBrandAdapter.BrandClickListener
{

    RecyclerView recyclerView;
    CarBrandAdapter adapter;
    List<CarBrand> brands;

    XMLCarBrandParser parser;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

        recyclerView = findViewById(R.id.brandsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.parser = new XMLCarBrandParser(this);

        brands = this.parser.getCarBrands();

        adapter = new CarBrandAdapter(this, brands, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onCarBrandClick(int position)
    {
        Intent intent = new Intent(this, BrandDetailActivity.class);

        CarBrand brand = brands.get(position);
        int brandImage =this.getResources().getIdentifier(brand.getImagePath(), "drawable", this.getPackageName());

        intent.putExtra("brandName", brand.getName());
        intent.putExtra("brandImage", brandImage);
        intent.putExtra("foundedYear", brand.getFoundedYear());
        intent.putExtra("country", brand.getCountry());
        intent.putExtra("url", brand.getUrl());
        intent.putExtra("carsCount", brand.getCars().size());

        for(int i = 0; i < brand.getCars().size(); i++)
        {
            List<CarModel> brandCars = brand.getCars();
            intent.putExtra("car" + i + "name", brandCars.get(i).getName());
            intent.putExtra("car" + i + "description", brandCars.get(i).getDescription());
            intent.putExtra("car" + i + "imagePath", brandCars.get(i).getImagePath());
        }

        startActivity(intent);
    }
}
