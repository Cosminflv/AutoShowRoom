package com.example.autoshowroomapp;

import java.io.Serializable;

public class Car implements Serializable {
    public int id;
    public String modelName;
    public String brandName;
    public int number;
    public String imageUrl;
    public String wikipediaUrl;
    public String fuelType;
    public int age;
    public int mileage;
    public String color;

    public Car() {
    }

    public Car(int id, String modelName, String brandName, int number, String imageUrl, String wikipediaUrl,
               String fuelType, int age, int mileage, String color) {
        this.id = id;
        this.modelName = modelName;
        this.brandName = brandName;
        this.number = number;
        this.imageUrl = imageUrl;
        this.wikipediaUrl = wikipediaUrl;
        this.fuelType = fuelType;
        this.age = age;
        this.mileage = mileage;
        this.color = color;
    }
}
