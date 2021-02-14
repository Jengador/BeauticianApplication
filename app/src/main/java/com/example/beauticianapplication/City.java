package com.example.beauticianapplication;

import java.util.ArrayList;

public class City implements Comparable<City>{
    int id;
    int key;
    String cityName;
    ArrayList<District> districtList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return this.cityName;
    }

    public int compareTo(City secondCity) {
        return this.getId() - secondCity.getId();
    }

    public ArrayList<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList<District> districtList) {
        this.districtList = districtList;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
