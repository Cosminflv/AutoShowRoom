package com.example.autoshowroom.entities;

import java.util.List;

public class CarBrand {
    String name;
    String imagePath;

    int foundedYear;
    String country;

    String url;

    List<CarModel> cars;

    public CarBrand(String name, String imagePath, int foundedYear, String country, String url, List<CarModel> cars) {
        this.name = name;
        this.imagePath = imagePath;
        this.foundedYear = foundedYear;
        this.country = country;
        this.url = url;
        this.cars = cars;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }
}
