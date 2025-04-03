package com.example.autoshowroomapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CarStorageHelper {
    private static final String FILE_NAME = "cars.json";

    public static void saveCars(Context context, ArrayList<Car> cars) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            new Gson().toJson(cars, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Car> loadCars(Context context) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            Log.d("CarStorageHelper", "JSON Content: " + json); // Add this line
            Type type = new TypeToken<ArrayList<Car>>(){}.getType();
            cars = new Gson().fromJson(json, type);
            br.close();
            Log.d("CarStorageHelper", "Loaded cars: " + (cars != null ? cars.size() : 0));
        } catch (Exception e) {
            Log.e("CarStorageHelper", "Error loading cars", e);
        }
        return cars != null ? cars : new ArrayList<>();
    }

    public static ArrayList<Car> loadInitialCarsFromAssets(Context context) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("cars.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type type = new TypeToken<ArrayList<Car>>(){}.getType();
            cars = new Gson().fromJson(reader, type);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static boolean dataExists(Context context) {
        return context.getFileStreamPath(FILE_NAME).exists();
    }
}
