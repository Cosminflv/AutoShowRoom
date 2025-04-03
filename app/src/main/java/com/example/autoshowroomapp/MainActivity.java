package com.example.autoshowroomapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarAdapter adapter;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewPlayers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ✅ Încarcă din JSON salvat, sau inițial din assets
        if (CarStorageHelper.dataExists(this)) {
            carList = CarStorageHelper.loadCars(this);
        } else {
            carList = CarStorageHelper.loadInitialCarsFromAssets(this);
            CarStorageHelper.saveCars(this, new ArrayList<>(carList));
        }

        adapter = new CarAdapter(this, carList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reîncarcă din JSON în caz că s-au făcut modificări
        if (carList != null) {
            carList.clear();
            carList.addAll(CarStorageHelper.loadCars(this));
            adapter.notifyDataSetChanged();
        }
    }
}
