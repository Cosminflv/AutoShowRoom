package com.example.autoshowroomapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageCarsActivity extends AppCompatActivity {
    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;

    private ArrayList<Car> cars = new ArrayList<>();
    private ManageCarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cars);

        // Replace with your own loading mechanism if needed
        cars = CarStorageHelper.loadCars(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewManage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManageCarAdapter(cars, this::onEditCar, this::onDeleteCar);
        recyclerView.setAdapter(adapter);

        Button buttonAdd = findViewById(R.id.buttonAddCar);
        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditCarActivity.class);
            startActivityForResult(intent, ADD_REQUEST);
        });
    }

    private void onEditCar(Car car, int position) {
        Intent intent = new Intent(this, AddEditCarActivity.class);
        intent.putExtra("car", car);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST);
    }

    private void onDeleteCar(int position) {
        cars.remove(position);
        CarStorageHelper.saveCars(this, cars);
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Car car = (Car) data.getSerializableExtra("car");
            int position = data.getIntExtra("position", -1);

            if (requestCode == ADD_REQUEST) {
                cars.add(car);
                CarStorageHelper.saveCars(this, cars);
                adapter.notifyItemInserted(cars.size() - 1);
            } else if (requestCode == EDIT_REQUEST && position != -1) {
                cars.set(position, car);
                CarStorageHelper.saveCars(this, cars);
                adapter.notifyItemChanged(position);
            }
        }
    }
}
