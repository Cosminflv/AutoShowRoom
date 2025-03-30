package com.example.autoshowroom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.entities.CarBrand;
import com.example.autoshowroom.utils.XMLCarBrandParser;

import java.util.List;

public class BrandsActivity extends AppCompatActivity implements CarBrandAdapter.BrandClickListener
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
    public void onCarBrandClick(int position) {

    }
}
